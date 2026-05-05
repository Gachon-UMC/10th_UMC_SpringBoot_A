package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false, length = 255)
    private String storeName;

    @Column(name = "manager_number", length = 20)
    private String managerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "detail_address", nullable = false, length = 255)
    private String detailAddress;

    @Column(name = "thumbnail_image_url", length = 500)
    private String thumbnailImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;

    @Column(name = "is_open", nullable = false)
    private Boolean open;

    @Builder
    private Store(
            String storeName,
            String managerNumber,
            Region region,
            String detailAddress,
            String thumbnailImageUrl,
            FoodCategory foodCategory,
            Boolean open
    ) {
        this.storeName = storeName;
        this.managerNumber = managerNumber;
        this.region = region;
        this.detailAddress = detailAddress;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.foodCategory = foodCategory;
        this.open = open;
    }
}