package com.umc10th.umc10th_kamang.domain.review.controller;

import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    /**
     * 리뷰 작성
     * POST /api/stores/{storeId}/reviews
     */
    @PostMapping("/api/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponse.CreateResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequest.CreateDTO request) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }
}
