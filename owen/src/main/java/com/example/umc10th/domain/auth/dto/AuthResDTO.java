package com.example.umc10th.domain.auth.dto;

import lombok.Builder;

public class AuthResDTO {

    @Builder
    public record SignUp(
            Long memberId,
            String email,
            String name
    ) {}

    @Builder
    public record Login(
            Long memberId,
            String accessToken,
            String refreshToken
    ) {}

    @Builder
    public record Preference(
            Long memberId,
            Integer registeredCount
    ) {}

    @Builder
    public record Terms(
            Long memberId
    ) {}
}