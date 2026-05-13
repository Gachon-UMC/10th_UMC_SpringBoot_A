package com.umc10th.umc10th_kamang.domain.review.entity;

import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Builder
@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id", unique = true)
    private UserMission userMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder.Default
    @ColumnDefault("5.0")
    @Column(name = "score", nullable = false, precision = 2, scale = 1)
    private BigDecimal score = new BigDecimal("5.0");

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
