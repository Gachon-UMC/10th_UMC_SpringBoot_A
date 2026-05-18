package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.review.entity.Review;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndUser(Long id, User user);

    @Query("SELECT r " +
            "FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.user = :user " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdSort(
            @Param("user") User user,
            Pageable pageable
    );

    @Query("SELECT r " +
            "FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.user = :user AND r.id < :cursor " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdSortWithCursor(
            @Param("user") User user,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("SELECT r " +
            "FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.user = :user " +
            "ORDER BY r.rate DESC, r.id DESC")
    Slice<Review> findMyReviewsByRatingSort(
            @Param("user") User user,
            Pageable pageable
    );

    @Query("SELECT r " +
            "FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.user = :user " +
            "AND (r.rate < :cursorRate OR (r.rate = :cursorRate AND r.id < :cursor)) " +
            "ORDER BY r.rate DESC, r.id DESC")
    Slice<Review> findMyReviewsByRatingSortWithCursor(
            @Param("user") User user,
            @Param("cursor") Long cursor,
            @Param("cursorRate") Float cursorRate,
            Pageable pageable
    );
}
