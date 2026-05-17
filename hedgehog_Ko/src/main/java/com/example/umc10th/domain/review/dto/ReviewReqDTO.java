package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            @Size(max = 500, message = "리뷰 내용은 500자 이하여야 합니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
            Double starRate,

            List<String> photoUrls
    ) {
    }

    public record UpdateReviewDTO(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            @Size(max = 500, message = "리뷰 내용은 500자 이하여야 합니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
            Double starRate,

            List<String> photoUrls
    ) {
    }
}
