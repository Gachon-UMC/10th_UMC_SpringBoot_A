package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // [7주차] 커서 기반 - ID 순 정렬 (최신순, ID 내림차순)
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND (:cursor IS NULL OR r.id < :cursor) " +
            "ORDER BY r.id DESC")
    List<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            org.springframework.data.domain.Pageable pageable
    );

    // [7주차] 커서 기반 - 별점 순 정렬 (높은 별점 먼저, 동점이면 ID 내림차순)
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND (:starCursor IS NULL OR r.star < :starCursor " +
            "     OR (r.star = :starCursor AND r.id < :idCursor)) " +
            "ORDER BY r.star DESC, r.id DESC")
    List<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("starCursor") Float starCursor,
            @Param("idCursor") Long idCursor,
            org.springframework.data.domain.Pageable pageable
    );

    List<Review> findByStoreId(Long storeId);
}