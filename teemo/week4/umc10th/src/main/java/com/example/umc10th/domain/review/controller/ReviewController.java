package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @PostMapping("/restaurants/{restaurantId}/reviews")
    @Operation(summary = "식당 리뷰 작성 API", description = "특정 식당에 리뷰를 작성하는 API입니다.")
    public ApiResponse<ReviewResDTO.ReviewResultDTO> createReview(
            @PathVariable Long restaurantId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateDTO request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/users/reviews")
    @Operation(summary = "나의 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 조회하는 API입니다.")
    @Parameter(name = "page", description = "페이지 번호 (0번부터 시작)")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getUserReviews(@RequestParam(defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(null);
    }
}
