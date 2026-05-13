package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "term")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermType name;

    // 양방향 매핑 제거
}