package com.umc10th.umc10th_kamang.domain.mission.controller;

import com.umc10th.umc10th_kamang.domain.mission.dto.MissionRequest;
import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    /**
     * 홈 화면 조회 (지역 기반 미션 목록)
     * GET /api/home
     */
    @GetMapping("/api/home")
    public ApiResponse<MissionResponse.HomeDTO> getHome(
            @RequestParam(required = false) Long regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }

    /**
     * 사용자 미션 목록 조회
     * GET /api/user-missions
     */
    @GetMapping("/api/user-missions")
    public ApiResponse<MissionResponse.MissionListDTO> getUserMissions(
            @RequestParam String status,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
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
