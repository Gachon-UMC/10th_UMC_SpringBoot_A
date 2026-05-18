package com.umc10th.umc10th_kamang.domain.user.dto;

import com.umc10th.umc10th_kamang.domain.user.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class UserRequest {

    /**
     * 회원가입 요청 DTO
     * POST /api/auth/signup
     */
    @Getter
    @Schema(description = "회원가입 요청")
    public static class SignupDTO {
        @Schema(description = "사용자 이름", example = "카망", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        private String name;

        @Schema(description = "성별", example = "MALE", allowableValues = { "MALE", "FEMALE", "NONE" }, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        private Gender gender;

        @Schema(description = "생년월일. YYYY-MM-DD 형식입니다.", example = "2001-02-24", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        private String birthDate;

        @Schema(description = "기본 주소", example = "서울특별시 송파구 문정동", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        private String address;

        @Schema(description = "상세 주소", example = "현대아파트 101동 101호")
        private String addressDetail;

        @Schema(description = "폼 로그인에 사용할 이메일", example = "kamang@gachon.ac.kr", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Email
        private String email;

        @Schema(description = "폼 로그인에 사용할 비밀번호. 저장 시 BCrypt로 암호화됩니다.", example = "password1234!", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        private String password;

        @Schema(description = "약관 동의 목록. 필수 약관은 반드시 동의해야 합니다.", requiredMode = Schema.RequiredMode.REQUIRED)
        @Valid
        @NotEmpty
        private List<TermAgreementDTO> termAgreements;

        @Schema(description = "선호 음식 카테고리 ID 목록. 1개 이상 전달합니다.", example = "[1, 3, 5]", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty
        private List<Long> foodCategoryIds;
    }

    /**
     * 약관 동의 개별 항목 DTO
     */
    @Getter
    @Schema(description = "약관 동의 요청 항목")
    public static class TermAgreementDTO {
        @Schema(description = "약관 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        private Long termId;

        @Schema(description = "동의 여부", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        private Boolean isAgreed;
    }
}
