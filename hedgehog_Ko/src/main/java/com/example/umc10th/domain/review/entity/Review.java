package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "star_rate", nullable = false)
    private Double starRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Review(
            String content,
            Double starRate,
            Store store,
            User user
    ) {
        this.content = content;
        this.starRate = starRate;
        this.store = store;
        this.user = user;
    }

    public void updateReview(String content, Double starRate) {
        this.content = content;
        this.starRate = starRate;
    }
}
