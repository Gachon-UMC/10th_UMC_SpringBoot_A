package com.example.umc10th.domain.review.entity;

public record ReviewPhoto(
    Long id,
    Long reviewId,
    String photoUrl
) {
}
