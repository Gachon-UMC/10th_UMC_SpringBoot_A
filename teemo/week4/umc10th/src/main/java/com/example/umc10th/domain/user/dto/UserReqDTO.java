package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class UserReqDTO {

    @Getter
    public static class JoinDTO {
        @NotBlank
        @Size(min = 2, max = 20)
        private String name;

        @NotNull
        private Integer gender;

        @NotNull
        private Integer birthYear;

        @NotNull
        private Integer birthMonth;

        @NotNull
        private Integer birthDay;

        @NotBlank
        @Size(min = 5, max = 100)
        private String address;

        @NotBlank
        @Size(min = 1, max = 100)
        private String specAddress;

        @Email
        @NotBlank
        private String email;

        @NotNull
        @Size(min = 1)
        private List<Long> preferCategory;
    }
}
