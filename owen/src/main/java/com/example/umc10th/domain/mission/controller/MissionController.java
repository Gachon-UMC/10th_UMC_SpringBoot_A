package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService; // 추가

    // [화면2] 미션 목록 조회: GET /api/missions?memberId=&isComplete=&page=&size=
    @GetMapping
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Long memberId, // 추가
            @RequestParam(required = false, defaultValue = "false") Boolean isComplete,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        MissionResDTO.MissionList result = missionService.getMyMissions(memberId, isComplete, page, size); // 서비스 연결
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, result);
    }

    // [화면4] 홈 화면 미션: GET /api/missions/home?address=&page=&size= (신규 추가)
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.MissionList> getHomeMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Address address,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        MissionResDTO.MissionList result = missionService.getHomeMissions(address, page, size); // 서비스 연결
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, result);
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