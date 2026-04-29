package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // POST /api/stores/{storeId}/reviews
    @Builder
    public record Create(
            Long reviewId,
            Long storeId,
            Float rating,
            String content
    ) {}

    // GET /api/stores/{storeId}/reviews?page=&size=
    @Builder
    public record ReviewList(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            List<ReviewItem> reviews
    ) {
        @Builder
        public record ReviewItem(
                Long reviewId,
                String memberName,
                Float rating,
                String content,
                LocalDateTime createdAt
        ) {}
    }
}