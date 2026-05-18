package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    public record ReviewResultDTO(
        Long reviewId,
        LocalDateTime createdAt
    ) {}

    public record ReviewPreviewDTO(
        Long reviewId,
        String storeName,
        Float rate,
        String content,
        LocalDateTime createdAt
    ) {}

    public record MyReviewListDTO(
        List<ReviewPreviewDTO> reviews,
        Integer listSize,
        Long nextCursor,
        Boolean isLast
    ) {}
}
