package com.example.umc10th.domain.mission.entity;

import java.time.LocalDateTime;

public record Mission(
    Long id,
    Long restaurantId,
    Long conditionPrice,
    Long awardPoint,
    LocalDateTime deadline,
    Boolean isDeleted
) {
}
