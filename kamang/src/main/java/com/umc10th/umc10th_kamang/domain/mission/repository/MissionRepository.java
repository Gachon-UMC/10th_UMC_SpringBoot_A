package com.umc10th.umc10th_kamang.domain.mission.repository;

import com.umc10th.umc10th_kamang.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(
            value = """
                    SELECT mission
                    FROM Mission mission
                    JOIN FETCH mission.store store
                    WHERE store.region.id = :regionId
                      AND mission.deadline > :now
                      AND NOT EXISTS (
                          SELECT userMission.id
                          FROM UserMission userMission
                          WHERE userMission.user.id = :userId
                            AND userMission.mission = mission
                      )
                    ORDER BY mission.deadline ASC, mission.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(mission)
                    FROM Mission mission
                    WHERE mission.store.region.id = :regionId
                      AND mission.deadline > :now
                      AND NOT EXISTS (
                          SELECT userMission.id
                          FROM UserMission userMission
                          WHERE userMission.user.id = :userId
                            AND userMission.mission = mission
                      )
                    """
    )
    Page<Mission> findChallengeableMissionsByRegion(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );
}
