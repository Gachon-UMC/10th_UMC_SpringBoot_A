package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "가게 리뷰 조회와 작성, 내 리뷰 조회, 수정, 삭제 API")
public interface ReviewControllerDocs {

    @Operation(
            summary = "가게 리뷰 목록 조회",
            description = "특정 가게에 작성된 리뷰 목록을 cursor 기반 페이지네이션으로 조회합니다. cursor를 생략하면 첫 페이지를 조회하고, 응답의 nextCursor는 다음 페이지 요청에 사용합니다. hasNext가 false이면 다음 페이지가 없으며, size는 한 번에 조회할 데이터 개수입니다."
    )
    ApiResponse<ReviewResDTO.StoreReviewListDTO> getStoreReviews(
            @Parameter(name = "storeId", description = "리뷰를 조회할 가게 ID", example = "1")
            Long storeId,

            @Parameter(name = "cursor", description = "다음 페이지 조회를 위한 cursor 값. 첫 조회 시 생략합니다.", example = "15")
            Long cursor,

            @Parameter(name = "size", description = "한 번에 조회할 리뷰 개수", example = "10")
            Integer size
    );

    @Operation(
            summary = "리뷰 작성",
            description = "특정 가게에 현재 사용자의 리뷰를 작성합니다."
    )
    ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @Parameter(name = "storeId", description = "리뷰를 작성할 가게 ID", example = "1")
            Long storeId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "리뷰 작성 요청 정보",
                    required = true
            )
            ReviewReqDTO.CreateReviewDTO request
    );

    @Operation(
            summary = "내 리뷰 목록 조회",
            description = "현재 사용자가 작성한 리뷰 목록을 cursor 기반 페이지네이션으로 조회합니다. cursor를 생략하면 첫 페이지를 조회하고, 응답의 nextCursor는 다음 페이지 요청에 사용합니다. hasNext가 false이면 다음 페이지가 없으며, size는 한 번에 조회할 데이터 개수입니다. sort는 id 또는 starRate 기준 정렬에 사용하고 기본값은 starRate입니다."
    )
    ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @Parameter(name = "cursor", description = "다음 페이지 조회를 위한 cursor 값. 첫 조회 시 생략합니다.", example = "20")
            Long cursor,

            @Parameter(name = "size", description = "한 번에 조회할 리뷰 개수", example = "10")
            Integer size,

            @Parameter(name = "sort", description = "정렬 기준. id 또는 starRate를 사용할 수 있으며 기본값은 starRate입니다.", example = "starRate")
            String sort
    );

    @Operation(
            summary = "내 리뷰 수정",
            description = "현재 사용자가 작성한 리뷰의 내용과 별점 등 수정 가능한 정보를 변경합니다."
    )
    ApiResponse<ReviewResDTO.UpdateReviewResultDTO> updateMyReview(
            @Parameter(name = "reviewId", description = "수정할 리뷰 ID", example = "1")
            Long reviewId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "리뷰 수정 요청 정보",
                    required = true
            )
            ReviewReqDTO.UpdateReviewDTO request
    );

    @Operation(
            summary = "내 리뷰 삭제",
            description = "현재 사용자가 작성한 리뷰를 삭제 처리합니다."
    )
    ApiResponse<ReviewResDTO.DeleteReviewResultDTO> deleteMyReview(
            @Parameter(name = "reviewId", description = "삭제할 리뷰 ID", example = "1")
            Long reviewId
    );
}
