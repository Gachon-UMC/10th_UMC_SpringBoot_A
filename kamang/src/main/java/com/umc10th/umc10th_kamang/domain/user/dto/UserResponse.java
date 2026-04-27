package com.umc10th.umc10th_kamang.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {

    /**
     * 회원가입 결과 응답 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupResultDTO {
        private Long userId;
        private LocalDateTime createdAt;
    }

    /**
     * 약관 목록 조회 응답 DTO
     * GET /api/auth/terms
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsListDTO {
        private List<TermDTO> terms;
    }

    /**
     * 약관 개별 항목 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermDTO {
        private Long termId;
        private String title;
        private String content;
        private Boolean isRequired;
    }
}
