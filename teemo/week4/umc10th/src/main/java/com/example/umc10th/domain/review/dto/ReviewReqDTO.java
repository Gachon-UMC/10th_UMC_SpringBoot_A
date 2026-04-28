package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateDTO {
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(min = 5, max = 500)
        private String content;

        @NotNull(message = "평점은 필수입니다.")
        @Min(1)
        @Max(5)
        private Integer rate;
    }
}
