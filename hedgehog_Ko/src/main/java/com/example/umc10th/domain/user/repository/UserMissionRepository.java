package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query(value = """
                SELECT um
                FROM UserMission um
                JOIN FETCH um.mission m
                JOIN FETCH m.store s
                JOIN FETCH s.foodCategory fc
                WHERE um.user.id = :userId
                  AND (:regionId IS NULL OR s.region.id = :regionId)
                  AND (:status IS NULL OR um.status = :status)
                  AND m.deletedAt IS NULL
                  AND s.deletedAt IS NULL
            """,
            countQuery = """
                        SELECT COUNT(um)
                        FROM UserMission um
                        JOIN um.mission m
                        JOIN m.store s
                        WHERE um.user.id = :userId
                          AND (:regionId IS NULL OR s.region.id = :regionId)
                          AND (:status IS NULL OR um.status = :status)
                          AND m.deletedAt IS NULL
                          AND s.deletedAt IS NULL
                    """)
    Page<UserMission> findMyMissions(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    @Query("""
                SELECT um
                FROM UserMission um
                JOIN FETCH um.user u
                JOIN FETCH um.mission m
                JOIN FETCH m.store s
                WHERE um.id = :userMissionId
                  AND m.deletedAt IS NULL
                  AND s.deletedAt IS NULL
            """)
    Optional<UserMission> findActiveUserMissionById(
            @Param("userMissionId") Long userMissionId
    );
}
