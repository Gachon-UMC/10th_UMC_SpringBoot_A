package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "리뷰 작성 API", description = "특정 가게에 리뷰를 작성하는 API입니다.")
    public ApiResponse<ReviewResDTO.ReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateDTO request) {
        return ApiResponse.onSuccess(null);
    }
}
