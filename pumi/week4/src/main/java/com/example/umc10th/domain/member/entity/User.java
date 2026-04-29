package com.example.umc10th.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record User(
    Long id,
    String email,
    String name,
    String nickname,
    String gender,
    LocalDate birthday,
    String address,
    Integer point,
    String role,
    String phoneNumber,
    Boolean isDeleted,
    LocalDateTime updatedAt,
    LocalDateTime createdAt
) {
}
