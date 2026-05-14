package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewReqDTO {

    public record CreateReview(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String reviewContent,
            @NotNull(message = "별점 기입은 필수입니다.")
            @DecimalMax("5.0")
            @DecimalMin("1.0")
            BigDecimal star){}
}
