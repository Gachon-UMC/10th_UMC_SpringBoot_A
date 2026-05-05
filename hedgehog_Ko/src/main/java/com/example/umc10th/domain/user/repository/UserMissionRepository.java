package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.foodCategory fc
        WHERE um.user.id = :userId
          AND (:regionId IS NULL OR s.region.id = :regionId)
          AND (:status IS NULL OR um.status = :status)
          AND (:cursor IS NULL OR um.id < :cursor)
          AND m.deletedAt IS NULL
          AND s.deletedAt IS NULL
        ORDER BY um.id DESC
    """)
    List<UserMission> findMyMissions(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId,
            @Param("status") MissionStatus status,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}