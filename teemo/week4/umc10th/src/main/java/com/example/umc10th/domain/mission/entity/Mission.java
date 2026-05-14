package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditional;

    @Column(nullable = false)
    private Integer point;

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}
