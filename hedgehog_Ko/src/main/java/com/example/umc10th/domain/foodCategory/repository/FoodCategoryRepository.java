package com.example.umc10th.domain.foodCategory.repository;

import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
