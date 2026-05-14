package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "사용자가 진행 중인 미션 목록을 오프셋 기반 페이지네이션으로 조회합니다.")
    public ApiResponse<MissionResDTO.MissionPreviewList> getOngoingMissions(
            @RequestBody @Valid MissionReqDTO.OngoingMissionList request) {
        return ApiResponse.of(GeneralSuccessCode.OK, missionService.getMyMissionList(request.getUserId(), false, request.getPage()));
    }

    @GetMapping("/regions")
    @Operation(summary = "지역 별 도전 가능 미션 조회 API", description = "현재 선택된 지역의 도전 가능한 미션 목록을 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionPreviewList> getMissionListByRegion(
            @RequestParam(name = "address") Address address,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.of(GeneralSuccessCode.OK, missionService.getMissionListByLocation(address, page));
    }

    @GetMapping("/regions/{regionId}/complete/count")
    @Operation(summary = "지역별 나의 완료한 미션 개수 조회 API", description = "특정 지역에서 내가 완료한 미션의 개수를 조회합니다.")
    public ApiResponse<MissionResDTO.MissionCount> getCompleteMissionCount(@PathVariable Long regionId) {
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{missionId}/complete")
    @Operation(summary = "미션 성공(완료) 누르기 API", description = "미션을 완료 상태로 변경합니다.")
    public ApiResponse<MissionResDTO.MissionCompleteResult> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionComplete request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "진행 중인 미션 등 홈 화면 정보를 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionPreviewList> getHome() {
        return ApiResponse.onSuccess(null);
    }
}
