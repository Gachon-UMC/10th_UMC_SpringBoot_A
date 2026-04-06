package com.example.umc10th.domain.member.entity;

import java.time.LocalDateTime;

public record NotificationSetting(
    Long id,
    Long userId,
    String type,
    Boolean isEnable,
    LocalDateTime updatedAt
) {
}
