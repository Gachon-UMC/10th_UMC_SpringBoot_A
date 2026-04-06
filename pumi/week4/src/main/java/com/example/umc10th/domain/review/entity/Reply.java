package com.example.umc10th.domain.review.entity;

import java.time.LocalDateTime;

public record Reply(
    Long id,
    Long reviewId,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean isDeleted
) {
}
