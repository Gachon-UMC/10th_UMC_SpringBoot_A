package com.umc10th.umc10th_kamang.domain.mission.repository;

import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
            SELECT COUNT(userMission)
            FROM UserMission userMission
            WHERE userMission.user.id = :userId
              AND userMission.status = :status
            """)
    Long countCompletedMissionsByUser(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status
    );

    @Query(
            value = """
                    SELECT userMission
                    FROM UserMission userMission
                    JOIN FETCH userMission.mission mission
                    JOIN FETCH mission.store store
                    WHERE userMission.user.id = :userId
                      AND userMission.status = :status
                    ORDER BY userMission.updatedAt DESC, userMission.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(userMission)
                    FROM UserMission userMission
                    WHERE userMission.user.id = :userId
                      AND userMission.status = :status
                    """
    )
    Page<UserMission> findUserMissionsByUserAndStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    @Query("""
            SELECT userMission
            FROM UserMission userMission
            WHERE userMission.user.id = :userId
              AND userMission.mission.store.id = :storeId
              AND userMission.status = :status
              AND NOT EXISTS (
                  SELECT review.id
                  FROM Review review
                  WHERE review.userMission = userMission
              )
            ORDER BY userMission.updatedAt DESC
            """)
    List<UserMission> findReviewableCompletedMissions(
            @Param("userId") Long userId,
            @Param("storeId") Long storeId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
