package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/missions/{missionId}")
    public Response<ReviewResponseDTO.ReviewResultDTO> createReview(
        @RequestParam Long userId,
        @PathVariable Long missionId,
        @RequestBody ReviewRequestDTO.WriteReviewDTO request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return Response.onSuccess(code, reviewService.createReview(userId, missionId, request));
    }
}
