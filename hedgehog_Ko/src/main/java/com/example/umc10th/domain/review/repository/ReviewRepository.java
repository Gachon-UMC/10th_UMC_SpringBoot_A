package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
                SELECT r
                FROM Review r
                JOIN FETCH r.user u
                WHERE r.store.id = :storeId
                  AND r.deletedAt IS NULL
                  AND (:cursor IS NULL OR r.id < :cursor)
                ORDER BY r.id DESC
            """)
    List<Review> findStoreReviews(
            @Param("storeId") Long storeId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
                SELECT r
                FROM Review r
                JOIN FETCH r.store s
                WHERE r.user.id = :userId
                  AND r.deletedAt IS NULL
                  AND (:cursor IS NULL OR r.id < :cursor)
                  AND s.deletedAt IS NULL
                ORDER BY r.starRate DESC, r.id DESC
            """)
    Slice<Review> findMyReviews(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
                SELECT r
                FROM Review r
                JOIN FETCH r.user u
                WHERE r.id = :reviewId
                  AND r.deletedAt IS NULL
            """)
    Optional<Review> findByIdAndDeletedAtIsNull(@Param("reviewId") Long reviewId);
}
