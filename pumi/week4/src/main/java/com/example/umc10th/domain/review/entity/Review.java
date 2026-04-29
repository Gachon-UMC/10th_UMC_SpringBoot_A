package com.example.umc10th.domain.review.entity;

import java.time.LocalDateTime;

public record Review(
    Long id,
    Long userMissionId,
    Float rate,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean isDeleted
) {
}
