package com.example.umc10th.domain.member.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    public record JoinResultDTO(
        Long memberId,
        LocalDateTime createdAt
    ) {}

    public record MemberInfoDTO(
        Long memberId,
        String name,
        String email,
        String nickname,
        String gender,
        String address
    ) {}

    public record NotificationPreviewDTO(
        Long notificationId,
        String title,
        String body,
        LocalDateTime createdAt
    ) {}

    public record NotificationListDTO(
        List<NotificationPreviewDTO> notifications
    ) {}
}
