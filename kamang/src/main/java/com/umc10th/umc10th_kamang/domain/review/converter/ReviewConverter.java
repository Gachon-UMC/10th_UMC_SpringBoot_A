package com.umc10th.umc10th_kamang.domain.review.converter;

import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.domain.review.entity.Review;
import com.umc10th.umc10th_kamang.domain.user.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static Review toReview(
            ReviewRequest.CreateDTO request,
            User user,
            Store store,
            UserMission userMission
    ) {
        return Review.builder()
                .user(user)
                .store(store)
                .userMission(userMission)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponse.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponse.CreateResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse.MyReviewListDTO toMyReviewListDTO(
            List<Review> reviews,
            boolean hasNext,
            boolean includeScoreCursor
    ) {
        List<ReviewResponse.MyReviewDTO> reviewDTOs = reviews.stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        // 응답에 포함된 마지막 리뷰를 다음 페이지 커서로 사용합니다.
        Review lastReview = hasNext && !reviews.isEmpty() ? reviews.get(reviews.size() - 1) : null;
        Long nextCursorId = lastReview == null ? null : lastReview.getId();
        BigDecimal nextCursorScore = includeScoreCursor && lastReview != null ? lastReview.getScore() : null;

        return ReviewResponse.MyReviewListDTO.builder()
                .reviews(reviewDTOs)
                .hasNext(hasNext)
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .build();
    }

    private static ReviewResponse.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResponse.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .nickname(review.getUser().getName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
