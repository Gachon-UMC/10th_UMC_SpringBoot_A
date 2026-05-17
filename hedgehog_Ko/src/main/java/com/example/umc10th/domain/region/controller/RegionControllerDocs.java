package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.dto.RegionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Region", description = "서비스 지역 조회 API")
public interface RegionControllerDocs {

    @Operation(
            summary = "지역 목록 조회",
            description = "서비스에서 사용할 수 있는 지역 목록을 조회합니다."
    )
    ApiResponse<RegionResDTO.RegionListDTO> getRegions();
}
