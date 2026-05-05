package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    public record JoinDTO(
        String name,
        String gender,
        LocalDate birthday,
        String address,
        List<Long> acceptance,
        List<Long> preferred_food
    ) {}

    public record UpdateDTO(
        String name
    ) {}

    public record NotificationSettingDTO(
        String type,
        Boolean isEnable
    ) {}

    public record UpdateNotificationSettingsDTO(
        List<NotificationSettingDTO> notification
    ) {}
}
