package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/me/missions")
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @Override
    @PostMapping
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @Valid @RequestBody MissionReqDTO.GetMyMissionsDTO request,
            @RequestParam(required = false) Long regionId,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        MissionResDTO.MyMissionListDTO response = missionService.getMyMissions(
                request.userId(),
                regionId,
                status,
                pageSize,
                pageNumber,
                sort
        );

        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_MISSION_LIST_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{userMissionId}")
    public ApiResponse<MissionResDTO.StartMissionResultDTO> startMission(
            @PathVariable Long userMissionId
    ) {
        Long userId = 1L;

        MissionResDTO.StartMissionResultDTO response = missionService.startMission(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.START_MISSION_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{userMissionId}/cancel")
    public ApiResponse<MissionResDTO.CancelMissionResultDTO> cancelMission(
            @PathVariable Long userMissionId
    ) {
        Long userId = 1L;

        MissionResDTO.CancelMissionResultDTO response = missionService.cancelMission(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.CANCEL_MISSION_SUCCESS, response);
    }

    @Override
    @PostMapping("/{userMissionId}/verification/request")
    public ApiResponse<MissionResDTO.VerificationRequestResultDTO> requestMissionVerification(
            @PathVariable Long userMissionId
    ) {
        Long userId = 1L;

        MissionResDTO.VerificationRequestResultDTO response =
                missionService.requestMissionVerification(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.REQUEST_MISSION_VERIFICATION_SUCCESS, response);
    }

    @Override
    @PostMapping("/{userMissionId}/verification/confirm")
    public ApiResponse<MissionResDTO.VerificationConfirmResultDTO> confirmMissionVerification(
            @PathVariable Long userMissionId
    ) {
        Long userId = 1L;

        MissionResDTO.VerificationConfirmResultDTO response =
                missionService.confirmMissionVerification(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.CONFIRM_MISSION_VERIFICATION_SUCCESS, response);
    }
}
