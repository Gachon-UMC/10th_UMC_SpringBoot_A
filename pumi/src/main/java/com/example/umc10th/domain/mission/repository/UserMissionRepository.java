package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Optional<UserMission> findByIdAndUserId(Long id, Long userId);

    @Query(value =
            "SELECT um " +
            "FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE um.user = :user " +
                "AND um.isCompleted = false " +
            "ORDER BY um.id DESC",
            countQuery =
            "SELECT COUNT(um) " +
            "FROM UserMission um " +
            "WHERE um.user = :user " +
                "AND um.isCompleted = false")
    Page<UserMission> findOngoingMissionsByUser(
            @Param("user") User user,
            Pageable pageable
    );

    @Query(value =
            "SELECT um " +
            "FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE um.user = :user " +
                "AND (:isCompleted IS NULL OR um.isCompleted = :isCompleted) " +
                "AND (:location IS NULL OR s.location = :location) " +
                "AND (:cursor IS NULL OR um.id < :cursor) " +
            "ORDER BY um.id DESC")
    Slice<UserMission> findByUserWithCursor(
            @Param("user") User user,
            @Param("isCompleted") Boolean isCompleted,
            @Param("location") Location location,
            @Param("cursor") Long cursor,
            Pageable pageable);

    @Query( "SELECT COUNT(um) " +
            "FROM UserMission um " +
            "WHERE um.user = :user " +
                "AND um.isCompleted = true " +
                "AND um.mission.store.location = :location")
    Long countSuccessfulMissionsByUserAndRegion(
            @Param("user") User user,
            @Param("location") Location location
    );
}
