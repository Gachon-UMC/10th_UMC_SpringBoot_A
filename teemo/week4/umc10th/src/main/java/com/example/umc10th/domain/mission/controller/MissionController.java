package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "사용자가 진행 중인 미션 목록을 오프셋 기반 페이지네이션으로 조회합니다.")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getOngoingMissions(
            @RequestBody @Valid MissionReqDTO.OngoingMissionListDTO request) {
        var userMissionPage = missionService.getMyMissionList(request.getUserId(), false, request.getPage());
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(userMissionPage));
    }

    @GetMapping("/regions")
    @Operation(summary = "지역 별 도전 가능 미션 조회 API", description = "현재 선택된 지역의 도전 가능한 미션 목록을 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionListByRegion(
            @RequestParam(name = "address") Address address,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<Mission> missionPage = missionService.getMissionListByLocation(address, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTOFromMission(missionPage));
    }

    @GetMapping("/regions/{regionId}/complete/count")
    @Operation(summary = "지역별 나의 완료한 미션 개수 조회 API", description = "특정 지역에서 내가 완료한 미션의 개수를 조회합니다.")
    public ApiResponse<MissionResDTO.MissionCountDTO> getCompleteMissionCount(@PathVariable Long regionId) {
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{missionId}/complete")
    @Operation(summary = "미션 성공(완료) 누르기 API", description = "미션을 완료 상태로 변경합니다.")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionCompleteDTO request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "진행 중인 미션 등 홈 화면 정보를 조회하는 API입니다.")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getHome() {
        return ApiResponse.onSuccess(null);
    }
}
