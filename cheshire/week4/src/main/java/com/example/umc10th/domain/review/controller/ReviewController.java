package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    @PostMapping("/my/review")
    public ApiResponse<String> createReview(
            @RequestBody ReviewReqDTO.CreateReview request
    ){
        // TODO: service 연결 예정
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_REGISTER_CREATED, "리뷰 작성 성공!");
    }
}
