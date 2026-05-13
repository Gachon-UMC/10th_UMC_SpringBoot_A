package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(
            ReviewReqDTO.CreateReviewDTO request,
            Store store,
            User user
    ) {
        return Review.builder()
                .content(request.content())
                .starRate(request.starRate())
                .store(store)
                .user(user)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.StoreReviewPreviewDTO toStoreReviewPreviewDTO(Review review) {
        User user = review.getUser();

        return ReviewResDTO.StoreReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .userId(user.getId())
                .userName(user.getNickname())
                .starRate(review.getStarRate())
                .content(review.getContent())
                .photoUrls(List.of())
                .reply(null)
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.StoreReviewListDTO toStoreReviewListDTO(
            List<Review> reviews,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
        return ReviewResDTO.StoreReviewListDTO.builder()
                .reviews(reviews.stream()
                        .map(ReviewConverter::toStoreReviewPreviewDTO)
                        .toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .size(size)
                .build();
    }

    public static ReviewResDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(Review review) {
        Store store = review.getStore();

        return ReviewResDTO.MyReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .starRate(review.getStarRate())
                .content(review.getContent())
                .photoUrls(List.of())
                .reply(null)
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(
            List<Review> reviews,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviews(reviews.stream()
                        .map(ReviewConverter::toMyReviewPreviewDTO)
                        .toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .size(size)
                .build();
    }

    public static ReviewResDTO.UpdateReviewResultDTO toUpdateReviewResultDTO(Review review) {
        return ReviewResDTO.UpdateReviewResultDTO.builder()
                .reviewId(review.getId())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static ReviewResDTO.DeleteReviewResultDTO toDeleteReviewResultDTO(Review review) {
        return ReviewResDTO.DeleteReviewResultDTO.builder()
                .reviewId(review.getId())
                .deletedAt(review.getDeletedAt())
                .build();
    }
}
