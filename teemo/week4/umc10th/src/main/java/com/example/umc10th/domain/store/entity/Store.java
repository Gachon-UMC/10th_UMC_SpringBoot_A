package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String managerNumber;

    @Column(nullable = false, length = 50)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
