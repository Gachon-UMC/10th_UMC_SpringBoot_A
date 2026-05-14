package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 작성 API", description = "특정 가게에 리뷰를 작성하는 API입니다.")
    public ApiResponse<ReviewResDTO.ReviewResult> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.Create request) {
        return ApiResponse.of(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, reviewService.createReview(storeId, request));
    }

    @PostMapping("/reviews/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회합니다. sortBy에 'id' 또는 'star'를 입력하세요.")
    public ApiResponse<ReviewResDTO.ReviewPreViewList> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.ListRequest request,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ApiResponse.of(GeneralSuccessCode.OK, reviewService.getUserReviewList(
                request.getUserId(),
                request.getCursorId(),
                request.getCursorStar(),
                request.getSortBy(),
                size));
    }
}
