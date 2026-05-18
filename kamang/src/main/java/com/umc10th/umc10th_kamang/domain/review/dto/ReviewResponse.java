package com.umc10th.umc10th_kamang.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 내가 작성한 리뷰 목록 응답 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "내가 작성한 리뷰 목록 응답")
    public static class MyReviewListDTO {
        @Schema(description = "리뷰 목록")
        private List<MyReviewDTO> reviews;
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        private Boolean hasNext;
        @Schema(description = "다음 페이지 조회용 리뷰 ID 커서", example = "12")
        private Long nextCursorId;
        @Schema(description = "다음 페이지 조회용 별점 커서. SCORE 정렬에서만 사용합니다.", example = "4.5")
        private BigDecimal nextCursorScore;
    }

    /**
     * 내가 작성한 리뷰 항목 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "내가 작성한 리뷰 항목")
    public static class MyReviewDTO {
        @Schema(description = "리뷰 ID", example = "1")
        private Long reviewId;
        @Schema(description = "가게 ID", example = "1")
        private Long storeId;
        @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
        private String storeName;
        @Schema(description = "작성자 닉네임. 현재는 사용자 이름을 사용합니다.", example = "닉네임1234")
        private String nickname;
        @Schema(description = "별점", example = "4.5")
        private BigDecimal score;
        @Schema(description = "리뷰 본문", example = "맛있어요")
        private String content;
        @Schema(description = "리뷰 작성 일시", example = "2026-05-05T12:00:00")
        private LocalDateTime createdAt;
    }
}
