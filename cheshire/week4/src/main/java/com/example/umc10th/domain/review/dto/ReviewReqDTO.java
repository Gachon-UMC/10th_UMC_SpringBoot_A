package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewReqDTO {

    @Getter
    public static class CreateReview {
        private String reviewContent;
        private BigDecimal star;
    }
}
