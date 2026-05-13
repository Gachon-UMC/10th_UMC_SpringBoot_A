package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;

public class ReviewConverter {

    public static Review toReview(
            ReviewReqDTO.CreateReviewDTO request,
            Store store,
            User user
    ) {
        return Review.builder()
                .content(request.content())
                .starRate(request.starRate())
                .store(store)
                .user(user)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
