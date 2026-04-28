package com.example.umc10th.domain.user.dto;

import java.util.List;

public class UserResDTO {

    public record MyPage(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    public record Profile(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber
    ) {
    }

    public record Point(
            Integer point
    ) {
    }

    public record ReviewPreview(
            Long reviewId,
            Long storeId,
            String storeName,
            Double star,
            String content
    ) {
    }

    public record ReviewList(
            Integer page,
            Integer size,
            Boolean hasNext,
            List<ReviewPreview> reviews
    ) {
    }

    public record InquiryPreview(
            Long inquiryId,
            String content,
            String status
    ) {
    }

    public record InquiryList(
            List<InquiryPreview> inquiries
    ) {
    }

    public record InquiryCreated(
            Long inquiryId
    ) {
    }

    public record NotificationSettings(
            Boolean reviewReplyNotificationEnabled
    ) {
    }

    public record PhoneVerificationResult(
            String phone,
            String status
    ) {
    }

    public record UserDeleted(
            Long userId
    ) {
    }
}