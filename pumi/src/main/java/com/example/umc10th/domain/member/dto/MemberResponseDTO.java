package com.example.umc10th.domain.member.dto;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    public record JoinResultDTO(
        Long memberId,
        LocalDateTime createdAt
    ) {}

    public record MemberInfoDTO(
        Long memberId,
        String name,
        String email,
        String nickname,
        String gender,
        String address
    ) {}
}
