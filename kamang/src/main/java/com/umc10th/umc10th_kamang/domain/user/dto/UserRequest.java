package com.umc10th.umc10th_kamang.domain.user.dto;

import com.umc10th.umc10th_kamang.domain.user.enums.Gender;
import lombok.Getter;

import java.util.List;

public class UserRequest {

    /**
     * 회원가입 요청 DTO
     * POST /api/auth/signup
     */
    @Getter
    public static class SignupDTO {
        private String name;
        private Gender gender;
        private String birthDate;        // YYYY-MM-DD
        private String address;
        private String addressDetail;    // 선택
        private String email;            // 선택
        private List<TermAgreementDTO> termAgreements;
        private List<Long> foodCategoryIds;
    }

    /**
     * 약관 동의 개별 항목 DTO
     */
    @Getter
    public static class TermAgreementDTO {
        private Long termId;
        private Boolean isAgreed;
    }
}
