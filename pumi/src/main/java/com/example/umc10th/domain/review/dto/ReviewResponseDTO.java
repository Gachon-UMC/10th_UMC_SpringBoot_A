package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    public record ReviewResultDTO(
        Long reviewId,
        LocalDateTime createdAt
    ) {}
}
