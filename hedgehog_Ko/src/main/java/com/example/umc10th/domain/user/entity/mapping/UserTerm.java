package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user_term",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_term_user_term", columnNames = {"user_id", "term_id"})
        }
)
public class UserTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_term_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    @Column(name = "agreed", nullable = false)
    private Boolean agreed;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @Builder
    private UserTerm(
            User user,
            Term term,
            Boolean agreed
    ) {
        this.user = user;
        this.term = term;
        this.agreed = agreed != null && agreed;

        if (this.agreed) {
            this.agreedAt = LocalDateTime.now();
        }
    }

    public void agree() {
        this.agreed = true;
        this.agreedAt = LocalDateTime.now();
    }

    public void disagree() {
        this.agreed = false;
        this.agreedAt = null;
    }
}
