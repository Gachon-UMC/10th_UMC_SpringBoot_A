package com.umc10th.umc10th_kamang.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponse {

    /**
     * 리뷰 작성 결과 응답 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 작성 결과 응답")
    public static class CreateResultDTO {
        @Schema(description = "생성된 리뷰 ID", example = "1")
        private Long reviewId;
        @Schema(description = "리뷰 작성 일시", example = "2026-05-05T12:00:00")
        private LocalDateTime createdAt;
    }
}
