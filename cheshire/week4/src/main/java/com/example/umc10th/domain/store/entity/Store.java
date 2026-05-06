package com.example.umc10th.domain.store.entity;


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
    private String storeCategory; // erd 상에는 String으로 되어있는데 그냥 enum으로 관리하는게 더 편할거같음..

//    식당 상세주소
    @Column(name = "store_address", nullable = false)
    private String storeAddress;
}
