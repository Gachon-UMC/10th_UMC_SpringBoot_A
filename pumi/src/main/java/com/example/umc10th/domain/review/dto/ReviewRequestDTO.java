package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewRequestDTO {

    public record WriteReviewDTO(
        Float rate,
        String content,
        List<String> images
    ) {}
}
