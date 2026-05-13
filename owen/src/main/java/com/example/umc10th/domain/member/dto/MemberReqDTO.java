package com.example.umc10th.domain.member.dto;

public class MemberReqDTO {

    // PATCH /api/members  (내 정보 수정)
    public record UpdateInfo(
            String name,
            String phoneNumber,
            String address,
            String detailAddress
    ) {}
}