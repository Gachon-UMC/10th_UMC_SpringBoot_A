package com.umc10th.umc10th_kamang.domain.review.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class ReviewRequest {

    /**
     * 리뷰 작성 요청 DTO
     * POST /api/stores/{storeId}/reviews
     */
    @Getter
    public static class CreateDTO {
        private BigDecimal score;   // 1.0 ~ 5.0
        private String content;     // 선택
        private List<String> imageUrls;  // 선택
    }
}
