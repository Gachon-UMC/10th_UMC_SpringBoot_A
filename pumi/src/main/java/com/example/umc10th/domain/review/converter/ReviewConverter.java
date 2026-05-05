package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.WriteReviewDTO request, User user, Store store, MemberMission memberMission) {
        return Review.builder()
            .rate(request.rate())
            .content(request.content())
            .user(user)
            .store(store)
            .memberMission(memberMission)
            .build();
    }

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return new ReviewResponseDTO.ReviewResultDTO(
            review.getId(),
            review.getCreatedAt() != null ? review.getCreatedAt() : LocalDateTime.now()
        );
    }
}
