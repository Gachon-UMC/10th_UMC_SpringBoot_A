package com.example.umc10th.domain.user.dto;

public class UserReqDTO {

    public record UpdateProfile(
            String nickname,
            String profileUrl
    ) {
    }

    public record PhoneVerificationRequest(
            String phone
    ) {
    }

    public record PhoneVerificationConfirm(
            String phone,
            String code
    ) {
    }

    public record CreateInquiry(
            String inquiry
    ) {
    }

    public record UpdateNotificationSettings(
            Boolean reviewReplyNotificationEnabled
    ) {
    }
}