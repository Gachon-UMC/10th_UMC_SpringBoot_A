package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewResult {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewList {
        private List<ReviewPreview> reviews;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreview {
        private Long reviewId;
        private String userName;
        private String storeName;
        private String body;
        private BigDecimal score;
        private LocalDateTime createdAt;
        // 사장님 답글 추가
        private String replyContent;
        private LocalDateTime replyCreatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewList {
        private List<ReviewPreview> reviewList;
        private Integer listSize;
        private Long totalReviewCount;
        private Long nextCursorId;
        private BigDecimal nextCursorStar;
        private Boolean hasNext;
    }
}
