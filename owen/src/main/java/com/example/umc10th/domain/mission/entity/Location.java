package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.store.entity.Store;  // ← 변경
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "location")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Address name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();
}