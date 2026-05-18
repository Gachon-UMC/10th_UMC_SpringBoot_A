package com.umc10th.umc10th_kamang.domain.review.repository;

import com.umc10th.umc10th_kamang.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * reviewId 기준 최신순 커서 조회
     */
    @Query("""
            SELECT review
            FROM Review review
            JOIN FETCH review.store store
            JOIN FETCH review.user user
            WHERE review.user.id = :userId
              AND (:cursorId IS NULL OR review.id < :cursorId)
            ORDER BY review.id DESC
            """)
    List<Review> findMyReviewsOrderByIdDesc(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    /**
     * 별점 높은순, 같은 별점에서는 reviewId 최신순 커서 조회
     */
    @Query("""
            SELECT review
            FROM Review review
            JOIN FETCH review.store store
            JOIN FETCH review.user user
            WHERE review.user.id = :userId
              AND (
                  (:cursorScore IS NULL AND :cursorId IS NULL)
                  OR review.score < :cursorScore
                  OR (review.score = :cursorScore AND review.id < :cursorId)
              )
            ORDER BY review.score DESC, review.id DESC
            """)
    List<Review> findMyReviewsOrderByScoreDesc(
            @Param("userId") Long userId,
            @Param("cursorScore") BigDecimal cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
