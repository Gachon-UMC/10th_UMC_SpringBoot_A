package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Store;  // ← 변경
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}