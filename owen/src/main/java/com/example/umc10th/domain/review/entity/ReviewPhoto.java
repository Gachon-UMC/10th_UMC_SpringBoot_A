package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "review_photo")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewPhoto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_photo_id")
    private Long id;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}