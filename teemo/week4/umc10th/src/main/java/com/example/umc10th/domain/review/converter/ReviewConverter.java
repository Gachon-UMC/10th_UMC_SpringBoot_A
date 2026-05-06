package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewCreateDTO request) {
        return Review.builder()
                .content(request.getContent())
                .star(request.getStar())
                .build();
    }

    public static ReviewResDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return ReviewResDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
