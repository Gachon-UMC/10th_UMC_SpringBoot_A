package com.example.umc10th.domain.term.entity;

import com.example.umc10th.domain.term.enums.TermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_type", nullable = false, length = 20)
    private TermType termType;

    @Column(name = "term_name", nullable = false, length = 100)
    private String termName;

    @Column(name = "required", nullable = false)
    private Boolean required;

    @Column(name = "term_content", columnDefinition = "TEXT")
    private String termContent;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private Term(
            TermType termType,
            String termName,
            Boolean required,
            String termContent
    ) {
        this.termType = termType;
        this.termName = termName;
        this.required = required;
        this.termContent = termContent;
    }
}