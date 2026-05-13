package com.umc10th.umc10th_kamang.domain.category.repository;

import com.umc10th.umc10th_kamang.domain.category.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
