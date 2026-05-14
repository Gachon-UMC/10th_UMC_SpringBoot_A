package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ApiResponse<ReviewResDTO.ReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateDTO request) {
        Review review = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }

    @PostMapping("/reviews/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회합니다. sortBy에 'id' 또는 'star'를 입력하세요.")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.ReviewListRequestDTO request,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        var reviewSlice = reviewService.getUserReviewList(
                request.getUserId(),
                request.getCursorId(),
                request.getCursorStar(),
                request.getSortBy(),
                size);
        Long totalCount = reviewService.getUserReviewCount(request.getUserId());
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewSlice, totalCount));
    }

    @GetMapping("/users/reviews")
    @Operation(summary = "나의 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 조회하는 API입니다.")
    @Parameter(name = "page", description = "페이지 번호 (0번부터 시작)")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getUserReviews(@RequestParam(defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(null);
    }
}
