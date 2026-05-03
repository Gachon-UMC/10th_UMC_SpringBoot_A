package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record StoreReviewListDTO(
            List<StoreReviewPreviewDTO> reviews,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
    }

    @Builder
    public record StoreReviewPreviewDTO(
            Long reviewId,
            Long userId,
            String userName,
            Double starRate,
            String content,
            List<String> photoUrls,
            ReplyDTO reply,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record MyReviewListDTO(
            List<MyReviewPreviewDTO> reviews,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
    }

    @Builder
    public record MyReviewPreviewDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            Double starRate,
            String content,
            List<String> photoUrls,
            ReplyDTO reply,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            Long storeId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record UpdateReviewResultDTO(
            Long reviewId,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record DeleteReviewResultDTO(
            Long reviewId,
            LocalDateTime deletedAt
    ) {
    }

    @Builder
    public record ReplyDTO(
            Long replyId,
            String content,
            LocalDateTime createdAt
    ) {
    }
}