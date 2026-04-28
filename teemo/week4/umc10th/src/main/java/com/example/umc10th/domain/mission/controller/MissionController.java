package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MissionController {

    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "진행 중인 미션 등 홈 화면 정보를 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.HomeDTO> getHome() {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/user-missions")
    @Operation(summary = "미션 목록 조회 API", description = "사용자의 미션 목록을 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionListDTO> getUserMissions() {
        return ApiResponse.onSuccess(null);
    }

    @PatchMapping("/user-missions/{userMissionId}")
    @Operation(summary = "미션 상태 변경 API", description = "특정 미션의 상태를 변경하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionUpdateResultDTO> updateMissionStatus(
            @PathVariable Long userMissionId,
            @RequestBody MissionReqDTO.MissionUpdateDTO request) {
        return ApiResponse.onSuccess(null);
    }
}
