package com.example.umc10th.domain.mission.entity.mapping;

import java.time.LocalDateTime;

public record MemberMission(
    Long id,
    Long userId,
    Long missionId,
    Boolean isCompleted,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean isDeleted
) {
}
