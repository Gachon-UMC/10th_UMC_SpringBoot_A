package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    // POST /auth/signup
    public record SignUp(
            @Valid @NotNull(message = "약관 동의 정보는 필수입니다.")
            Agree agree,

            @NotBlank(message = "이름은 필수입니다.")
            @Size(max = 5, message = "이름은 최대 5자입니다.")
            String name,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,

            @NotNull(message = "주소는 필수입니다.")
            Address address,

            String detailAddress,

            @NotNull(message = "선호 음식 목록은 필수입니다. 없으면 빈 배열을 보내주세요.")
            List<FoodCategory> foodList,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            @Size(max = 50)
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 4, max = 64, message = "비밀번호는 4~64자입니다.")
            String password
    ) {
        // 약관 동의 (만 14세 + 4종 약관)
        public record Agree(
                @NotNull(message = "만 14세 이상 동의 여부는 필수입니다.") Boolean age,
                @NotNull(message = "서비스 이용약관 동의 여부는 필수입니다.") Boolean service,
                @NotNull(message = "개인정보 처리방침 동의 여부는 필수입니다.") Boolean privacy,
                @NotNull Boolean location,
                @NotNull Boolean marketing
        ) {}
    }

    // POST /auth/preferences
    public record Preference(
            List<Long> categoryIds
    ) {}

    // POST /auth/terms
    public record Terms(
            List<Long> agreedTermIds
    ) {}
}
