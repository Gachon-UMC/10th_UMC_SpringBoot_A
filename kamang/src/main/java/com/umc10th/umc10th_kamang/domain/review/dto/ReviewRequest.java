package com.umc10th.umc10th_kamang.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class ReviewRequest {

    /**
     * 리뷰 작성 요청 DTO
     * POST /api/stores/{storeId}/reviews
     */
    @Getter
    @Schema(description = "리뷰 작성 요청")
    public static class CreateDTO {
        @Schema(description = "별점. 1.0 이상 5.0 이하", example = "4.0", requiredMode = Schema.RequiredMode.REQUIRED)
        private BigDecimal score;   // 1.0 ~ 5.0
        @Schema(description = "리뷰 본문. 사진만 등록하는 경우 생략 가능", example = "맛있어요")
        private String content;     // 선택
        @Schema(description = "첨부 이미지 URL 목록. 현재 단계에서는 저장하지 않고 무시합니다.")
        private List<String> imageUrls;  // 선택
    }
}
