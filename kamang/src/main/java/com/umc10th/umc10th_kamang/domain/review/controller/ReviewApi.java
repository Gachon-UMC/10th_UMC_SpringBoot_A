package com.umc10th.umc10th_kamang.domain.review.controller;

import com.umc10th.umc10th_kamang.domain.review.dto.ReviewRequest;
import com.umc10th.umc10th_kamang.domain.review.dto.ReviewResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;

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

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "사용자가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회합니다. sortBy는 ID 또는 SCORE를 지원하며, 사진 정보는 응답에서 제외합니다."
    )
    ApiResponse<ReviewResponse.MyReviewListDTO> getMyReviews(
            @Parameter(name = "userId", description = "임시 사용자 ID", example = "1", required = true)
            Long userId,
            @Parameter(name = "sortBy", description = "정렬 기준. ID 또는 SCORE", example = "ID", required = true)
            String sortBy,
            @Parameter(name = "cursorId", description = "다음 페이지 조회용 마지막 리뷰 ID", example = "12")
            Long cursorId,
            @Parameter(name = "cursorScore", description = "SCORE 정렬에서 사용하는 다음 페이지 조회용 마지막 별점", example = "4.5")
            BigDecimal cursorScore,
            @Parameter(name = "size", description = "조회 개수", example = "10")
            Integer size);
}
