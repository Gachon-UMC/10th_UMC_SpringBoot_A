package com.example.umc10th.domain.term.entity;

import com.example.umc10th.domain.term.enums.TermType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_type", nullable = false, length = 20)
    private TermType type;

    @Column(name = "term_name", nullable = false, length = 100)
    private String name;

    @Column(name = "required", nullable = false)
    private Boolean required;

    @Column(name = "term_content", columnDefinition = "TEXT")
    private String content;

    @Builder
    private Term(
            TermType type,
            String name,
            Boolean required,
            String content
    ) {
        this.type = type;
        this.name = name;
        this.required = required;
        this.content = content;
    }
}
