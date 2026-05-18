package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.WriteReviewDTO request, User user, Store store, UserMission userMission) {
        return Review.builder()
            .rate(request.rate())
            .content(request.content())
            .user(user)
            .store(store)
            .userMission(userMission)
            .build();
    }

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return new ReviewResponseDTO.ReviewResultDTO(
            review.getId(),
            review.getCreatedAt() != null ? review.getCreatedAt() : LocalDateTime.now()
        );
    }

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return new ReviewResponseDTO.ReviewPreviewDTO(
                review.getId(),
                review.getStore().getName(),
                review.getRate(),
                review.getContent(),
                review.getCreatedAt()
        );
    }

    public static ReviewResponseDTO.MyReviewListDTO toMyReviewListDTO(Slice<Review> reviewSlice) {
        List<Review> content = reviewSlice.getContent();
        List<ReviewResponseDTO.ReviewPreviewDTO> previewDTOs = content.stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .collect(Collectors.toList());

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return new ReviewResponseDTO.MyReviewListDTO(
                previewDTOs,
                previewDTOs.size(),
                nextCursor,
                reviewSlice.isLast()
        );
    }
}
