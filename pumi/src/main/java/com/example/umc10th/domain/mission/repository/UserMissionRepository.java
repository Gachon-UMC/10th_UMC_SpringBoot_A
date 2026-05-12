package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

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
