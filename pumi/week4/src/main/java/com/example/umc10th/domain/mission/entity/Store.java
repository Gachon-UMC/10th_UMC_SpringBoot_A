package com.example.umc10th.domain.mission.entity;

public record Store(
    Long id,
    Long regionId,
    Long ownerId,
    String name,
    String type,
    String ownerNumber,
    Boolean isDeleted
) {
}
