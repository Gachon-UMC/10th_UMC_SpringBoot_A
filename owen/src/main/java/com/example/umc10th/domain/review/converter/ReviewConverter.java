package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.store.entity.Store;  // ← 변경
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toEntity(ReviewReqDTO.Create req, Member member, Store store) {
        return Review.builder()
                .content(req.content())
                .star(req.rating())
                .createdAt(LocalDateTime.now())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.Create toCreateRes(Review review) {
        return new ReviewResDTO.Create(
                review.getId(),
                review.getStore().getId(),
                review.getStar(),
                review.getContent()
        );
    }
}