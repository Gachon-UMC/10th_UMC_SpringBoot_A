package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewReqDTO {

    public record CreateReview(
            String reviewContent,
            BigDecimal star){}
}
