package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // [7주차] 내가 진행중인 미션 조회 (오프셋 기반 페이지네이션, memberId를 RequestBody에서 받기)
    @PostMapping("/my")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @Valid @RequestBody MissionReqDTO.MyMissionReq req
    ) {
        MissionResDTO.MissionList result = missionService.getMyMissions(
                req.memberId(), req.isComplete(), req.page(), req.size());
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, result);
    }

    // [6주차 유지] 미션 목록 조회
    @GetMapping
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Long memberId,
            @RequestParam(required = false, defaultValue = "false") Boolean isComplete,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        MissionResDTO.MissionList result = missionService.getMyMissions(memberId, isComplete, page, size);
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, result);
    }

    // 홈 화면 미션 — 반환 타입을 HomeMissionList로 변경
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissionList> getHomeMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Address address,
            @RequestParam Long memberId,  // 이미 도전한 미션 제외용
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        MissionResDTO.HomeMissionList result = missionService.getHomeMissions(address, memberId, page, size);
        return ApiResponse.onSuccess(MissionSuccessCode.LIST_OK, result);
    }

    // [6주차 유지] 미션 도전
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.Challenge> challenge(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.CHALLENGE_OK, null);
    }

    // [6주차 유지] 미션 성공 요청
    @PostMapping("/{missionId}/complete-request")
    public ApiResponse<MissionResDTO.CompleteRequest> completeRequest(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_REQUEST_OK, null);
    }

    // [6주차 유지] 미션 성공 인증
    @PatchMapping("/{missionId}/verify")
    public ApiResponse<MissionResDTO.Verify> verify(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.VERIFY_OK, null);
    }
}