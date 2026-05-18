package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    public record CreateDTO(
        @NotBlank
        @Email
        @Size(max = 20)
        String email,
        @NotBlank
        @Size(max = 20)
        String name,
        @NotBlank
        @Size(max = 100)
        String password,
        @Size(max = 10)
        String gender,
        LocalDate birthday,
        @Size(max = 100)
        String address,
        List<Long> acceptance,
        List<Long> preferred_food
    ) {}

    public record UpdateDTO(
        @NotBlank
        @Size(max = 20)
        String name
    ) {}

    public record LoginDTO(
        @NotBlank
        @Email
        @Size(max = 20)
        String email,
        @NotBlank
        @Size(max = 100)
        String password
    ) {}

    public record NotificationSettingDTO(
        @NotBlank
        String type,
        @NotNull
        Boolean isEnable
    ) {}

    public record UpdateNotificationSettingsDTO(
        @NotNull
        @Size(min = 1)
        List<NotificationSettingDTO> notification
    ) {}
}
