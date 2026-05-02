package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    // 미션 목록 조회: GET /api/missions?isComplete=&page=&size=
    @GetMapping
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) Boolean isComplete,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, null);
    }

    // 미션 도전 하기: POST /api/missions/{missionId}/challenge
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.Challenge> challenge(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.CHALLENGE_OK, null);
    }

    // 미션 성공 요청: POST /api/missions/{missionId}/complete-request
    @PostMapping("/{missionId}/complete-request")
    public ApiResponse<MissionResDTO.CompleteRequest> completeRequest(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_REQUEST_OK, null);
    }

    // 미션 성공 인증: PATCH /api/missions/{missionId}/verify
    @PatchMapping("/{missionId}/verify")
    public ApiResponse<MissionResDTO.Verify> verify(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.VERIFY_OK, null);
    }
}