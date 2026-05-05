package com.example.umc10th.domain.foodCategory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_category")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    @Column(name = "food_category_name", nullable = false, length = 50, unique = true)
    private String foodCategoryName;

    @Builder
    private FoodCategory(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

}
