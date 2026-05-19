package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="review", nullable = false)
    private String review;

    @Column(name="star", nullable = false)
    private Float star;

    @OneToOne
    @JoinColumn(name="response_id")
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

}
