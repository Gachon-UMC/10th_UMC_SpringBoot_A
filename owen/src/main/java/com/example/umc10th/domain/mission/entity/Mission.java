package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Store;  // ← 변경
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "mission")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditional;

    @Column(nullable = false)
    private Integer point;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();
}