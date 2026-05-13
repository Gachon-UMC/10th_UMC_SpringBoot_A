package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    // 기존 유지
    public static Review toEntity(ReviewReqDTO.Create req, Member member, Store store) {
        return Review.builder()
                .content(req.content())
                .star(req.rating())
                // createdAt 제거 — BaseEntity의 @CreatedDate가 자동 처리
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

    // [7주차] 내가 작성한 리뷰 → 커서 기반 응답 DTO (ID 순)
    public static ReviewResDTO.MyReviewList toMyReviewListById(List<Review> reviews, int size) {
        boolean hasNext = reviews.size() > size;
        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        List<ReviewResDTO.MyReviewList.MyReviewItem> items = content.stream()
                .map(r -> new ReviewResDTO.MyReviewList.MyReviewItem(
                        r.getId(),
                        r.getStore().getName(),
                        r.getStar(),
                        r.getContent(),
                        r.getCreatedAt()
                ))
                .toList();

        Long nextCursor = hasNext ? content.get(content.size() - 1).getId() : null;

        return new ReviewResDTO.MyReviewList(items, nextCursor, null, null, hasNext);
    }

    // [7주차] 내가 작성한 리뷰 → 커서 기반 응답 DTO (별점 순)
    public static ReviewResDTO.MyReviewList toMyReviewListByStar(List<Review> reviews, int size) {
        boolean hasNext = reviews.size() > size;
        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        List<ReviewResDTO.MyReviewList.MyReviewItem> items = content.stream()
                .map(r -> new ReviewResDTO.MyReviewList.MyReviewItem(
                        r.getId(),
                        r.getStore().getName(),
                        r.getStar(),
                        r.getContent(),
                        r.getCreatedAt()
                ))
                .toList();

        Float nextStarCursor = null;
        Long nextIdCursor = null;
        if (hasNext) {
            Review last = content.get(content.size() - 1);
            nextStarCursor = last.getStar();
            nextIdCursor = last.getId();
        }

        return new ReviewResDTO.MyReviewList(items, null, nextStarCursor, nextIdCursor, hasNext);
    }
}