package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        ReviewResDTO.StoreReviewListDTO response = reviewService.getStoreReviews(storeId, cursor, size);

        return ApiResponse.onSuccess(ReviewSuccessCode.GET_STORE_REVIEW_LIST_SUCCESS, response);
    }

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        Long userId = 1L;

        ReviewResDTO.CreateReviewResultDTO response = reviewService.createReview(userId, storeId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW_SUCCESS, response);
    }

    @GetMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Long userId = 1L;

        ReviewResDTO.MyReviewListDTO response = reviewService.getMyReviews(userId, cursor, size, sort);

        return ApiResponse.onSuccess(ReviewSuccessCode.GET_MY_REVIEW_LIST_SUCCESS, response);
    }

    @PatchMapping("/users/me/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReviewResultDTO> updateMyReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.UpdateReviewDTO request
    ) {
        Long userId = 1L;

        ReviewResDTO.UpdateReviewResultDTO response = reviewService.updateMyReview(userId, reviewId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.UPDATE_REVIEW_SUCCESS, response);
    }

    @DeleteMapping("/users/me/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.DeleteReviewResultDTO> deleteMyReview(
            @PathVariable Long reviewId
    ) {
        Long userId = 1L;

        ReviewResDTO.DeleteReviewResultDTO response = reviewService.deleteMyReview(userId, reviewId);

        return ApiResponse.onSuccess(ReviewSuccessCode.DELETE_REVIEW_SUCCESS, response);
    }
}
