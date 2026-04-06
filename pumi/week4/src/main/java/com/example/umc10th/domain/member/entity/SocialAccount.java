package com.example.umc10th.domain.member.entity;

public record SocialAccount(
    Long id,
    Long userId,
    String provider,
    String providerId
) {
}
