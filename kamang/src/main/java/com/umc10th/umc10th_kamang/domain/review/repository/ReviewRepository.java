package com.umc10th.umc10th_kamang.domain.review.repository;

import com.umc10th.umc10th_kamang.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
