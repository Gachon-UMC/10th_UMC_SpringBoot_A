package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    public record ReviewResultDTO(
        Long reviewId,
        LocalDateTime createdAt
    ) {}

    public record NotificationPreviewDTO(
        Long notificationId,
        String title,
        String body,
        LocalDateTime createdAt
    ) {}

    public record NotificationListDTO(
        List<NotificationPreviewDTO> notifications
    ) {}
}
