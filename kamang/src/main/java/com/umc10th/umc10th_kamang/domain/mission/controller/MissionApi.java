package com.umc10th.umc10th_kamang.domain.mission.controller;

import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mission", description = "홈 화면 및 사용자 미션 관련 API")
public interface MissionApi {

    @Operation(
            summary = "홈 화면 조회",
            description = "현재 선택된 지역 기준으로 도전 가능한 미션 목록을 페이징 조회합니다. 인증 구현 전까지 userId를 query parameter로 전달합니다."
    )
    ApiResponse<MissionResponse.HomeDTO> getHome(
            @Parameter(name = "userId", description = "임시 사용자 ID", example = "1", required = true)
            Long userId,
            @Parameter(name = "regionId", description = "조회할 지역 ID. 미입력 시 사용자의 현재 설정 지역을 사용합니다.", example = "1")
            Long regionId,
            @Parameter(name = "page", description = "페이지 번호", example = "0")
            Integer page,
            @Parameter(name = "size", description = "페이지 크기", example = "10")
            Integer size);

    @Operation(
            summary = "내 미션 목록 조회",
            description = "사용자의 진행중 또는 진행완료 미션 목록을 페이징 조회합니다. status는 PROCEEDING 또는 COMPLETED만 허용합니다."
    )
    ApiResponse<MissionResponse.MissionListDTO> getUserMissions(
            @Parameter(name = "userId", description = "임시 사용자 ID", example = "1", required = true)
            Long userId,
            @Parameter(name = "status", description = "미션 상태 필터", example = "PROCEEDING", required = true)
            String status,
            @Parameter(name = "page", description = "페이지 번호", example = "0")
            Integer page,
            @Parameter(name = "size", description = "페이지 크기", example = "10")
            Integer size);
}
