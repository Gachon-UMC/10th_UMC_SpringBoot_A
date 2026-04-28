package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateDTO {
        @NotBlank
        private String body;
        @NotNull
        private Float score;
    }
}
