package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public Response<ReviewResponseDTO.ReviewResultDTO> writeReview(@RequestBody ReviewRequestDTO.WriteReviewDTO request) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return Response.onSuccess(code, reviewService.writeReview(request));
    }

    @PutMapping("/notifications/settings")
    public Response<String> updateNotificationSettings(@RequestBody ReviewRequestDTO.UpdateNotificationSettingsDTO request) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        reviewService.updateNotificationSettings(request);
        return Response.onSuccess(code, "알림 설정 수정 완료");
    }

    @GetMapping("/notifications")
    public Response<ReviewResponseDTO.NotificationListDTO> getNotifications() {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return Response.onSuccess(code, reviewService.getNotifications());
    }
}
