package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateDTO {
        @NotNull(message = "작성자 ID는 필수입니다.")
        private Long userId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(min = 5, max = 500)
        private String content;

        @NotNull(message = "평점은 필수입니다.")
        @DecimalMin(value = "1.0")
        @DecimalMax(value = "5.0")
        private BigDecimal star;
    }

    @Getter
    public static class ReviewListRequestDTO {
        @NotNull
        private Long userId;
        private Long cursorId;
        private BigDecimal cursorStar;
        private String sortBy; // "id" or "star"
    }
}
