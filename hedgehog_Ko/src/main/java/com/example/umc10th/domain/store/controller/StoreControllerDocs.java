package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Store", description = "지역별 가게 목록과 가게 상세 조회 API")
public interface StoreControllerDocs {

    @Operation(
            summary = "지역별 가게 목록 조회",
            description = "특정 지역의 가게 목록을 cursor 기반 페이지네이션으로 조회합니다. cursor를 생략하면 첫 페이지를 조회하고, 응답의 nextCursor는 다음 페이지 요청에 사용합니다. hasNext가 false이면 다음 페이지가 없으며, size는 한 번에 조회할 데이터 개수입니다."
    )
    ApiResponse<StoreResDTO.StoreListDTO> getStoreList(
            @Parameter(name = "regionId", description = "가게를 조회할 지역 ID", example = "1")
            Long regionId,

            @Parameter(name = "cursor", description = "다음 페이지 조회를 위한 cursor 값. 첫 조회 시 생략합니다.", example = "10")
            Long cursor,

            @Parameter(name = "size", description = "한 번에 조회할 가게 개수", example = "10")
            Integer size
    );

    @Operation(
            summary = "가게 상세 조회",
            description = "가게 ID로 가게 기본 정보, 음식 카테고리, 평균 별점, 리뷰 수, 리뷰 미리보기 등을 조회합니다."
    )
    ApiResponse<StoreResDTO.StoreDetailDTO> getStoreDetail(
            @Parameter(name = "storeId", description = "상세 정보를 조회할 가게 ID", example = "1")
            Long storeId
    );
}
