package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "WHERE l.name = :address " +
            "AND m.deletedAt IS NULL " +
            "AND m.deadline >= CURRENT_DATE " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findAvailableMissionsByRegion(
            @Param("address") Address address,
            Pageable pageable
    );
}