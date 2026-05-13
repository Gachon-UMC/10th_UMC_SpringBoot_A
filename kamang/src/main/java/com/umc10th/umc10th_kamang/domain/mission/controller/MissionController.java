package com.umc10th.umc10th_kamang.domain.mission.controller;

import com.umc10th.umc10th_kamang.domain.mission.dto.MissionRequest;
import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.domain.mission.service.MissionService;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Mission", description = "홈 화면 및 사용자 미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    /**
     * 홈 화면 조회 (지역 기반 미션 목록)
     * GET /api/home
     */
    @GetMapping("/api/home")
    @Operation(
            summary = "홈 화면 조회",
            description = "현재 선택된 지역 기준으로 도전 가능한 미션 목록을 페이징 조회합니다. 인증 구현 전까지 userId를 query parameter로 전달합니다."
    )
    public ApiResponse<MissionResponse.HomeDTO> getHome(
            @Parameter(description = "임시 사용자 ID", example = "1", required = true)
            @RequestParam Long userId,
            @Parameter(description = "조회할 지역 ID. 미입력 시 사용자의 현재 설정 지역을 사용합니다.", example = "1")
            @RequestParam(required = false) Long regionId,
            @Parameter(description = "페이지 번호", example = "0")
            @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "10")
            @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.onSuccess(missionService.getHome(userId, regionId, page, size));
    }

    /**
     * 사용자 미션 목록 조회
     * GET /api/users/me/missions
     */
    @GetMapping("/api/users/me/missions")
    @Operation(
            summary = "내 미션 목록 조회",
            description = "사용자의 진행중 또는 진행완료 미션 목록을 페이징 조회합니다. status는 PROCEEDING 또는 COMPLETED만 허용합니다."
    )
    public ApiResponse<MissionResponse.MissionListDTO> getUserMissions(
            @Parameter(description = "임시 사용자 ID", example = "1", required = true)
            @RequestParam Long userId,
            @Parameter(description = "미션 상태 필터", example = "PROCEEDING", required = true)
            @RequestParam String status,
            @Parameter(description = "페이지 번호", example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "10")
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ApiResponse.onSuccess(missionService.getUserMissions(userId, status, page, size));
    }

    /**
     * 미션 상태 변경
     * PATCH /api/user-missions/{userMissionId}
     */
    @PatchMapping("/api/user-missions/{userMissionId}")
    public ApiResponse<MissionResponse.UpdateStatusResultDTO> updateMissionStatus(
            @PathVariable Long userMissionId,
            @RequestBody MissionRequest.UpdateStatusDTO request) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }
}
