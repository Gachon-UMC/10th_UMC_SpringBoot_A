package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            String content,
            Double starRate,
            List<String> photoUrls
    ) {
    }

    public record UpdateReviewDTO(
            String content,
            Double starRate,
            List<String> photoUrls
    ) {
    }
}
