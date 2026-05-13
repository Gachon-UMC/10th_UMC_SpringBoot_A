package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "store")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private Long managerNumber;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}