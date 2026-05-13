package com.umc10th.umc10th_kamang.domain.review.controller;

import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 관련 API")
public interface ReviewApi {

    @Operation(
            summary = "리뷰 작성",
            description = "완료된 미션을 기준으로 가게 리뷰를 작성합니다. 사진 URL은 현재 단계에서 저장하지 않고 무시합니다."
    )
    ApiResponse<ReviewResponse.CreateResultDTO> createReview(
            @Parameter(name = "storeId", description = "리뷰를 작성할 가게 ID", example = "1", required = true)
            Long storeId,
            @Parameter(name = "userId", description = "임시 사용자 ID", example = "1", required = true)
            Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 작성 요청", required = true)
            ReviewRequest.CreateDTO request);
}
