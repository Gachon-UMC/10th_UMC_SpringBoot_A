package com.example.umc10th.domain.member.entity;

import java.time.LocalDateTime;

public record Notification(
    Long id,
    Long userId,
    String title,
    String body,
    LocalDateTime createdAt
) {
}
