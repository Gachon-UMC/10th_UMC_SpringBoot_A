package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "reply")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}