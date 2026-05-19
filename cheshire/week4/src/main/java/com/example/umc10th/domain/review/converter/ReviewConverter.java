package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review){
        return ReviewResDTO.ReviewInfo.builder()
                .id(review.getId())
                .name(review.getMember().getName())
                .star(review.getStar())
                .review(review.getReview())
                .createdAt(review.getCreatedAt())
                .responseContent(review.getReply() != null ? review.getReply().getContent() : null)
                .responseCreatedAt(review.getReply() != null ? review.getReply().getCreatedAt() : null)
                .storeId(review.getStore().getId())
                .build();

    }
}
