package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.Create request) {
        return Review.builder()
                .content(request.getContent())
                .star(request.getStar())
                .build();
    }

    public static ReviewResDTO.ReviewResult toReviewResultDTO(Review review) {
        return ReviewResDTO.ReviewResult.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt() : LocalDateTime.now())
                .build();
    }

    public static ReviewResDTO.ReviewPreview toReviewPreview(Review review) {
        return ReviewResDTO.ReviewPreview.builder()
                .reviewId(review.getId())
                .userName(review.getUser().getName())
                .storeName(review.getStore().getName())
                .body(review.getContent())
                .score(review.getStar())
                .createdAt(review.getCreatedAt())
                .replyContent(review.getReply() != null ? review.getReply().getContent() : null)
                .replyCreatedAt(review.getReply() != null ? review.getReply().getCreatedAt() : null)
                .build();
    }

    public static ReviewResDTO.ReviewPreViewList toReviewPreViewList(Slice<Review> reviewSlice, Long totalCount) {
        List<ReviewResDTO.ReviewPreview> reviewPreviewList = reviewSlice.getContent().stream()
                .map(ReviewConverter::toReviewPreview)
                .collect(Collectors.toList());

        Long nextCursorId = null;
        BigDecimal nextCursorStar = null;
        if (reviewSlice.hasNext() && !reviewPreviewList.isEmpty()) {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorStar = lastReview.getStar();
        }

        return ReviewResDTO.ReviewPreViewList.builder()
                .reviewList(reviewPreviewList)
                .listSize(reviewPreviewList.size())
                .totalReviewCount(totalCount)
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .hasNext(reviewSlice.hasNext())
                .build();
    }
}
