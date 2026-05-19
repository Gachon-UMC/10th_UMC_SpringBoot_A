package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.enums.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // [8주차] FoodCategory 로 마스터 음식 카테고리 조회
    Optional<Food> findByName(FoodCategory name);
}