package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ReviewRequestDTO {

    public enum ReviewSortType {
        ID, RATING
    }

    public record WriteReviewDTO(
        @NotNull
        @Min(0)
        @Max(5)
        Float rate,
        @NotBlank
        String content,
        List<String> images
    ) {}

    public record MyReviewsRequestDTO(
        @Min(1)
        Long cursor,
        @NotNull
        @Min(1)
        Integer size,
        @NotNull
        ReviewSortType sort
    ) {}
}
