package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // POST /api/stores/{storeId}/reviews (기존)
    @Builder
    public record Create(
            Long reviewId,
            Long storeId,
            Float rating,
            String content
    ) {}

    // GET /api/stores/{storeId}/reviews (기존)
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

    // [7주차] 내가 작성한 리뷰 목록 (커서 기반 페이지네이션)
    @Builder
    public record MyReviewList(
            List<MyReviewItem> reviews,
            Long nextCursor,       // ID 순 정렬 시 다음 커서 (마지막 리뷰 ID)
            Float nextStarCursor,  // 별점 순 정렬 시 다음 커서 (마지막 리뷰 별점)
            Long nextIdCursor,     // 별점 순 정렬 시 동점 구분용 ID
            Boolean hasNext        // 다음 페이지 존재 여부
    ) {
        @Builder
        public record MyReviewItem(
                Long reviewId,
                String storeName,
                Float star,
                String content,
                LocalDateTime createdAt
        ) {}
    }
}