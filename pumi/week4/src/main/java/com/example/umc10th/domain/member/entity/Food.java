package com.example.umc10th.domain.member.entity;

import java.time.LocalDateTime;

public record Food(
    Long id,
    String food,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean isDeleted
) {
}
