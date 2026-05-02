package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    // 리뷰 작성: POST /api/stores/{storeId}/reviews
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.Create dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_OK, null);
    }

    // 식당 정보 리뷰 조회: GET /api/stores/{storeId}/reviews?page=&size=
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewList> getReviews(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.LIST_OK, null);
    }
}