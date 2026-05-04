package com.umc10th.umc10th_kamang.domain.review.converter;

import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.domain.review.entity.Review;
import com.umc10th.umc10th_kamang.domain.user.entity.User;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static Review toReview(
            ReviewRequest.CreateDTO request,
            User user,
            Store store,
            UserMission userMission
    ) {
        return Review.builder()
                .user(user)
                .store(store)
                .userMission(userMission)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponse.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponse.CreateResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
