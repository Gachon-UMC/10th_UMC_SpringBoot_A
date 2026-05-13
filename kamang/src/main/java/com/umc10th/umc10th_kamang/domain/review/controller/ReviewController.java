package com.umc10th.umc10th_kamang.domain.review.controller;

import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.domain.review.service.ReviewService;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * POST /api/stores/{storeId}/reviews
     */
    @PostMapping("/api/stores/{storeId}/reviews")
    @Operation(
            summary = "리뷰 작성",
            description = "완료된 미션을 기준으로 가게 리뷰를 작성합니다. 사진 URL은 현재 단계에서 저장하지 않고 무시합니다."
    )
    public ApiResponse<ReviewResponse.CreateResultDTO> createReview(
            @Parameter(description = "리뷰를 작성할 가게 ID", example = "1", required = true)
            @PathVariable Long storeId,
            @Parameter(description = "임시 사용자 ID", example = "1", required = true)
            @RequestParam Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 작성 요청", required = true)
            @RequestBody ReviewRequest.CreateDTO request) {
        return ApiResponse.onSuccess(reviewService.createReview(userId, storeId, request));
    }
}
