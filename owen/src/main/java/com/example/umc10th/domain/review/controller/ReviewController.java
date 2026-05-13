package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // [6주차 유지] 리뷰 작성 — @Valid 추가 (과제3)
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @Valid @RequestBody ReviewReqDTO.Create dto
    ) {
        ReviewResDTO.Create result = reviewService.createReview(storeId, memberId, dto);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_OK, result);
    }

    // [6주차 유지] 식당 리뷰 조회
    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewList> getReviews(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.LIST_OK, null);
    }

    // [7주차] 내가 작성한 리뷰 조회 - ID 순 (커서 기반)
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviewsById(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewResDTO.MyReviewList result = reviewService.getMyReviewsById(memberId, cursor, size);
        return ApiResponse.onSuccess(ReviewSuccessCode.LIST_OK, result);
    }

    // [7주차] 내가 작성한 리뷰 조회 - 별점 순 (커서 기반)
    @GetMapping("/members/{memberId}/reviews/by-star")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviewsByStar(
            @PathVariable Long memberId,
            @RequestParam(required = false) Float starCursor,
            @RequestParam(required = false) Long idCursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewResDTO.MyReviewList result = reviewService.getMyReviewsByStar(memberId, starCursor, idCursor, size);
        return ApiResponse.onSuccess(ReviewSuccessCode.LIST_OK, result);
    }
}