package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 정렬
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id > :cursor ORDER BY r.id ASC")
    List<Review> findMyReviewsWithCursorOrderById(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    // 별점 순 정렬
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id > :cursor ORDER BY r.star DESC")
    List<Review> findMyReviewsWithCursorOrderByStar(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}