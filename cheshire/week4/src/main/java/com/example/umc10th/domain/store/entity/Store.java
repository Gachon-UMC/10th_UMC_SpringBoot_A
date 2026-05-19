package com.example.umc10th.domain.store.entity;


import com.example.umc10th.domain.member.enums.FoodName;
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
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    지역id
    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

//    사장님 구분번호
    @Column(name = "number", nullable = false)
    private Long number;

//    식당명
    @Column(name = "store_name", nullable = false)
    private String storeName;

//    식당 카테고리
    @Column(name = "store_category", nullable = false)
    private FoodName storeCategory;

//    식당 상세주소
    @Column(name = "store_address", nullable = false)
    private String storeAddress;
}
