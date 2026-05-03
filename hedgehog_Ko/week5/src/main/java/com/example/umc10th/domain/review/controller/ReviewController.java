package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.StoreReviewListDTO> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<ReviewResDTO.StoreReviewPreviewDTO> reviews = List.of(
                ReviewResDTO.StoreReviewPreviewDTO.builder()
                        .reviewId(1L)
                        .userId(1L)
                        .userName("홍길동")
                        .starRate(5.0)
                        .content("맛있고 매장이 깔끔해요.")
                        .photoUrls(List.of(
                                "https://example.com/review1-1.png",
                                "https://example.com/review1-2.png"
                        ))
                        .reply(ReviewResDTO.ReplyDTO.builder()
                                .replyId(1L)
                                .content("방문해주셔서 감사합니다.")
                                .createdAt(LocalDateTime.now().minusDays(1))
                                .build())
                        .createdAt(LocalDateTime.now().minusDays(2))
                        .build(),
                ReviewResDTO.StoreReviewPreviewDTO.builder()
                        .reviewId(2L)
                        .userId(2L)
                        .userName("김철수")
                        .starRate(4.5)
                        .content("양도 괜찮고 맛있었습니다.")
                        .photoUrls(List.of())
                        .reply(null)
                        .createdAt(LocalDateTime.now().minusDays(3))
                        .build()
        );

        ReviewResDTO.StoreReviewListDTO response = ReviewResDTO.StoreReviewListDTO.builder()
                .reviews(reviews)
                .nextCursor(2L)
                .hasNext(true)
                .size(reviews.size())
                .build();

        return ApiResponse.onSuccess(ReviewSuccessCode.GET_STORE_REVIEW_LIST_SUCCESS, response);
    }

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        ReviewResDTO.CreateReviewResultDTO response = ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(1L)
                .storeId(storeId)
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW_SUCCESS, response);
    }

    @GetMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        List<ReviewResDTO.MyReviewPreviewDTO> reviews = List.of(
                ReviewResDTO.MyReviewPreviewDTO.builder()
                        .reviewId(1L)
                        .storeId(1L)
                        .storeName("반이학생마라탕")
                        .starRate(5.0)
                        .content("맛있고 매장이 깔끔해요.")
                        .photoUrls(List.of("https://example.com/my-review1.png"))
                        .reply(ReviewResDTO.ReplyDTO.builder()
                                .replyId(1L)
                                .content("방문해주셔서 감사합니다.")
                                .createdAt(LocalDateTime.now().minusDays(1))
                                .build())
                        .createdAt(LocalDateTime.now().minusDays(2))
                        .updatedAt(LocalDateTime.now().minusDays(1))
                        .build(),
                ReviewResDTO.MyReviewPreviewDTO.builder()
                        .reviewId(2L)
                        .storeId(2L)
                        .storeName("요아정 가천대점")
                        .starRate(4.5)
                        .content("디저트가 맛있었습니다.")
                        .photoUrls(List.of())
                        .reply(null)
                        .createdAt(LocalDateTime.now().minusDays(5))
                        .updatedAt(LocalDateTime.now().minusDays(5))
                        .build()
        );

        ReviewResDTO.MyReviewListDTO response = ReviewResDTO.MyReviewListDTO.builder()
                .reviews(reviews)
                .nextCursor(2L)
                .hasNext(true)
                .size(reviews.size())
                .build();

        return ApiResponse.onSuccess(ReviewSuccessCode.GET_MY_REVIEW_LIST_SUCCESS, response);
    }

    @PatchMapping("/users/me/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReviewResultDTO> updateMyReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.UpdateReviewDTO request,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        ReviewResDTO.UpdateReviewResultDTO response = ReviewResDTO.UpdateReviewResultDTO.builder()
                .reviewId(reviewId)
                .updatedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(ReviewSuccessCode.UPDATE_REVIEW_SUCCESS, response);
    }

    @DeleteMapping("/users/me/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.DeleteReviewResultDTO> deleteMyReview(
            @PathVariable Long reviewId,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        ReviewResDTO.DeleteReviewResultDTO response = ReviewResDTO.DeleteReviewResultDTO.builder()
                .reviewId(reviewId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(ReviewSuccessCode.DELETE_REVIEW_SUCCESS, response);
    }
}
