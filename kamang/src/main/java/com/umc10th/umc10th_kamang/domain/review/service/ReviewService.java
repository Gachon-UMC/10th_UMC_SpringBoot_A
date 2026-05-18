package com.umc10th.umc10th_kamang.domain.review.service;

import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionErrorCode;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionException;
import com.umc10th.umc10th_kamang.domain.mission.repository.StoreRepository;
import com.umc10th.umc10th_kamang.domain.mission.repository.UserMissionRepository;
import com.umc10th.umc10th_kamang.domain.review.converter.ReviewConverter;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.domain.review.entity.Review;
import com.umc10th.umc10th_kamang.domain.review.enums.ReviewSortType;
import com.umc10th.umc10th_kamang.domain.review.exception.ReviewErrorCode;
import com.umc10th.umc10th_kamang.domain.review.exception.ReviewException;
import com.umc10th.umc10th_kamang.domain.review.repository.ReviewRepository;
import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.domain.user.exception.UserErrorCode;
import com.umc10th.umc10th_kamang.domain.user.exception.UserException;
import com.umc10th.umc10th_kamang.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    // 커서 기반 페이지네이션 기본 조회 개수
    private static final int DEFAULT_SIZE = 10;

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public ReviewResponse.CreateResultDTO createReview(
            Long userId,
            Long storeId,
            ReviewRequest.CreateDTO request
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        UserMission userMission = findReviewableCompletedMission(userId, storeId);

        Review review = ReviewConverter.toReview(request, user, store, userMission);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateResultDTO(savedReview);
    }

    public ReviewResponse.MyReviewListDTO getMyReviews(
            Long userId,
            String sortBy,
            Long cursorId,
            BigDecimal cursorScore,
            Integer size
    ) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        ReviewSortType sortType = parseSortType(sortBy);
        validateCursor(sortType, cursorId, cursorScore);

        int resolvedSize = size == null ? DEFAULT_SIZE : size;
        // 다음 페이지 존재 여부를 확인하기 위해 요청 개수보다 1개 더 조회합니다.
        int querySize = resolvedSize + 1;

        List<Review> fetchedReviews = findMyReviews(userId, sortType, cursorId, cursorScore, querySize);
        boolean hasNext = fetchedReviews.size() > resolvedSize;
        List<Review> reviews = hasNext ? fetchedReviews.subList(0, resolvedSize) : fetchedReviews;

        return ReviewConverter.toMyReviewListDTO(reviews, hasNext, sortType == ReviewSortType.SCORE);
    }

    private ReviewSortType parseSortType(String sortBy) {
        try {
            return ReviewSortType.valueOf(sortBy);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ReviewException(ReviewErrorCode.REVIEW_SORT_INVALID);
        }
    }

    private void validateCursor(ReviewSortType sortType, Long cursorId, BigDecimal cursorScore) {
        // ID 정렬은 reviewId만으로 다음 페이지 위치를 판단합니다.
        if (sortType == ReviewSortType.ID && cursorScore != null) {
            throw new ReviewException(ReviewErrorCode.REVIEW_CURSOR_INVALID);
        }

        // 별점 정렬은 같은 별점 내 순서를 보장하기 위해 score와 reviewId를 함께 사용합니다.
        if (sortType == ReviewSortType.SCORE && ((cursorId == null) != (cursorScore == null))) {
            throw new ReviewException(ReviewErrorCode.REVIEW_CURSOR_INVALID);
        }
    }

    private List<Review> findMyReviews(
            Long userId,
            ReviewSortType sortType,
            Long cursorId,
            BigDecimal cursorScore,
            int querySize
    ) {
        PageRequest pageRequest = PageRequest.of(0, querySize);

        if (sortType == ReviewSortType.ID) {
            return reviewRepository.findMyReviewsOrderByIdDesc(userId, cursorId, pageRequest);
        }

        return reviewRepository.findMyReviewsOrderByScoreDesc(userId, cursorScore, cursorId, pageRequest);
    }

    private UserMission findReviewableCompletedMission(Long userId, Long storeId) {
        List<UserMission> userMissions = userMissionRepository.findReviewableCompletedMissions(
                userId,
                storeId,
                MissionStatus.COMPLETED,
                PageRequest.of(0, 1)
        );

        if (userMissions.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_ALLOWED);
        }

        return userMissions.get(0);
    }
}
