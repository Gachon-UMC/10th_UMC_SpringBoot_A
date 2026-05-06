package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    @Query(
        value = "SELECT mm FROM MemberMission mm " +
                "JOIN FETCH mm.mission m " +
                "JOIN FETCH m.store " +
                "WHERE mm.member.id = :memberId AND mm.status = :status",
        countQuery = "SELECT COUNT(mm) FROM MemberMission mm " +
                     "WHERE mm.member.id = :memberId AND mm.status = :status"
    )
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") Status status,
            Pageable pageable
    );
    long countByMemberIdAndStatus(Long memberId, Status status);
}
