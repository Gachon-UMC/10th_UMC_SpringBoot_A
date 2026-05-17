package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    // ReqDTO는 주로 Request Body가 있는 경우에만 적는다고 한다.
    // static class 방식 예시 (java 원래 문법)
// 회원 가입
    @Getter
    @NoArgsConstructor
    public static class SignupDTO {
        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotBlank(message = "닉네임은 필수입니다.")
        private String nickname;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생년월일은 필수입니다.")
        private LocalDate birth;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @NotBlank(message = "상세 주소는 필수입니다.")
        private String detailAddress;

        private String socialUid;

        private SocialType socialType;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
        private String password;

        @NotBlank(message = "전화번호는 필수입니다.")
        private String phoneNumber;

        private String profileImageUrl;

        @NotEmpty(message = "선호 음식 카테고리는 하나 이상 선택해야 합니다.")
        private List<@NotNull(message = "음식 카테고리 ID는 필수입니다.") @Positive(message = "음식 카테고리 ID는 양수여야 합니다.") Long> foodCategoryIds;

        @NotEmpty(message = "약관 동의 정보는 하나 이상 필요합니다.")
        @Valid
        private List<TermAgreementDTO> terms;
    }

    @Getter
    @NoArgsConstructor
    public static class TermAgreementDTO {
        @NotNull(message = "약관 ID는 필수입니다.")
        @Positive(message = "약관 ID는 양수여야 합니다.")
        private Long termId;

        @NotNull(message = "약관 동의 여부는 필수입니다.")
        private Boolean agreed;
    }

    // record 방식
    // 내 정보 수정
    public record UpdateMyInfoDTO(
            String nickname,
            String address,
            String detailAddress,
            String profileImageUrl
    ) {
    }

    // 내 알람 설정 수정
    public record UpdateNotificationSettingDTO(
            Boolean reviewReplyNotification
    ) {
    }

}
