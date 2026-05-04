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

    private static final BigDecimal MIN_SCORE = new BigDecimal("1.0");
    private static final BigDecimal MAX_SCORE = new BigDecimal("5.0");

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
        validateScore(request.getScore());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        UserMission userMission = findReviewableCompletedMission(userId, storeId);

        Review review = ReviewConverter.toReview(request, user, store, userMission);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateResultDTO(savedReview);
    }

    private void validateScore(BigDecimal score) {
        if (score == null || score.compareTo(MIN_SCORE) < 0 || score.compareTo(MAX_SCORE) > 0) {
            throw new ReviewException(ReviewErrorCode.INVALID_SCORE);
        }
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
