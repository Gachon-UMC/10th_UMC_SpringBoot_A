package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/my/review")
    public ApiResponse<String> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ){
        reviewService.createReview(request,1L,1L);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_REGISTER_CREATED, "리뷰 작성 성공!");
    }

    @GetMapping("/my/reviews")
    public ApiResponse<?> getMyReviews(
            @RequestBody  Long memberId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ID") String sort
    ){
        ReviewResDTO.MyReviewResDTO result = reviewService.getMyReviews(
                memberId,
                cursor,
                size,
                sort
        );

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_OK, result);

    }
}
