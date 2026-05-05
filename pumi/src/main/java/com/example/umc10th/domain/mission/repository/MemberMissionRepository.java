package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(value =
            "SELECT mm " +
            "FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE mm.user = :user " +
                "AND (:isCompleted IS NULL OR mm.isCompleted = :isCompleted) " +
                "AND (:location IS NULL OR s.location = :location) " +
                "AND (:cursor IS NULL OR mm.id < :cursor) " +
            "ORDER BY mm.id DESC")
    Slice<MemberMission> findByUserIdWithCursor(
            @Param("user") User user,
            @Param("isCompleted") Boolean isCompleted,
            @Param("location") Location location,
            @Param("cursor") Long cursor,
            Pageable pageable);

    @Query( "SELECT COUNT(mm) " +
            "FROM MemberMission mm " +
            "WHERE mm.user = :user " +
                "AND mm.isCompleted = true " +
                "AND mm.mission.store.location = :location")
    Long countSuccessfulMissionsByUserIdAndRegionId(
            @Param("user") User user,
            @Param("location") Location location
    );
}
