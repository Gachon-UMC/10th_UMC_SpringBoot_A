package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    // POST /api/stores/{storeId}/reviews (기존)
    public record Create(
            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 1, message = "별점은 1 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5 이하여야 합니다.")
            Float rating,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content
    ) {}
}