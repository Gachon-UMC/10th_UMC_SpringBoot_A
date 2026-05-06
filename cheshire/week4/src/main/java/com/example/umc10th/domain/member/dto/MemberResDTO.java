package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {
    public record ProfileRes(
            String name,
            String email,
            Long phoneNumber,
            Integer totalPoint){}
}
