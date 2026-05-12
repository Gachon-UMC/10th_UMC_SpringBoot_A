package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.PreferredFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferredFoodRepository extends JpaRepository<PreferredFood, Long> {
}
