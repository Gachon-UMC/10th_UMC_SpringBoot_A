package com.umc10th.umc10th_kamang.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "회원가입 결과 응답")
    public static class SignupResultDTO {
        @Schema(description = "생성된 사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "회원가입 처리 시각", example = "2026-05-19T02:10:00")
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
    @Schema(description = "약관 목록 조회 응답")
    public static class TermsListDTO {
        @Schema(description = "약관 목록")
        private List<TermDTO> terms;
    }

    /**
     * 약관 개별 항목 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "약관 항목")
    public static class TermDTO {
        @Schema(description = "약관 ID", example = "1")
        private Long termId;

        @Schema(description = "약관 제목", example = "서비스 이용약관")
        private String title;

        @Schema(description = "약관 내용", example = "서비스 이용을 위한 약관 내용입니다.")
        private String content;

        @Schema(description = "필수 약관 여부", example = "true")
        private Boolean isRequired;
    }

    /**
     * 마이페이지 조회 응답 DTO
     * GET /api/users/me
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "마이페이지 조회 응답")
    public static class MyPageDTO {
        @Schema(description = "사용자 ID", example = "1")
        private Long userId;
        @Schema(description = "닉네임. 현재는 사용자 이름을 사용합니다.", example = "nickname012")
        private String nickname;
        @Schema(description = "이메일", example = "dlapdlf@naver.com")
        private String email;
        @Schema(description = "휴대폰 번호", example = "01012345678")
        private String phoneNumber;
        @Schema(description = "휴대폰 인증 여부", example = "false")
        private Boolean isPhoneVerified;
        @Schema(description = "보유 포인트", example = "2500")
        private Integer totalPoints;
    }
}
