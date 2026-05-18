package com.umc10th.umc10th_kamang.domain.review.controller;

import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.domain.review.service.ReviewService;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * POST /api/stores/{storeId}/reviews
     */
    @PostMapping("/api/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponse.CreateResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @Valid @RequestBody ReviewRequest.CreateDTO request) {
        return ApiResponse.onSuccess(reviewService.createReview(userId, storeId, request));
    }

    /**
     * 내가 작성한 리뷰 목록 조회
     * GET /api/users/me/reviews
     */
    @GetMapping("/api/users/me/reviews")
    public ApiResponse<ReviewResponse.MyReviewListDTO> getMyReviews(
            @RequestParam Long userId,
            @RequestParam String sortBy,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) BigDecimal cursorScore,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(
                userId,
                sortBy,
                cursorId,
                cursorScore,
                size
        ));
    }
}
