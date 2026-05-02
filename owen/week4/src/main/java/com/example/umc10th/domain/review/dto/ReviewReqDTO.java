package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    // POST /api/stores/{storeId}/reviews
    public record Create(
            Float rating,
            String content
    ) {}
}