package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.mapping.UserPreferenceFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceFoodRepository extends JpaRepository<UserPreferenceFood, Long> {
}
