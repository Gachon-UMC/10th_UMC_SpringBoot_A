package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.user = :user AND (:cursorId IS NULL OR r.id < :cursorId) ORDER BY r.id DESC")
    Slice<Review> findAllByUserOrderByIdDesc(@Param("user") User user, @Param("cursorId") Long cursorId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user = :user AND (:cursorStar IS NULL OR (r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId))) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findAllByUserOrderByStarDescIdDesc(@Param("user") User user, @Param("cursorStar") BigDecimal cursorStar, @Param("cursorId") Long cursorId, Pageable pageable);

    Long countByUser(User user);
}
