package com.example.umc10th.domain.user.dto;


import com.example.umc10th.domain.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record SignupResultDTO(
            Long userId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record MyInfoDTO(
            Long userId,
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Gender gender,
            LocalDate birth,
            String address,
            Integer point
    ) {
    }

    @Builder
    public record UpdateMyInfoResultDTO(
            Long userId,
            String nickName,
            String phoneNumber,
            LocalDateTime updatedAt
    ) {
    }

    // static class 일때 예시!!
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteUserResultDTO {
        private Long userId;
        private LocalDateTime deletedAt;
    }

    @Builder
    public record PointDTO(
            Integer point
    ) {
    }

    @Builder
    public record NotificationSettingDTO(
            Boolean reviewReplyNotification
    ) {
    }
}