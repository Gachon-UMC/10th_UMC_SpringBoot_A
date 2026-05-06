package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.store.enums.Address;
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
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private Address name;

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();
}
