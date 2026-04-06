package com.example.umc10th.domain.member.entity;

import java.time.LocalDateTime;

public record Term(
    Long id,
    String type,
    String body,
    String version,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean isDeleted
) {
}
