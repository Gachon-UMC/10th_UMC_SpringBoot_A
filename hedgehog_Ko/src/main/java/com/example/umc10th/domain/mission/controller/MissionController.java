package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.auth.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/me/missions")
public class MissionController {

    private final MissionService missionService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(required = false) Long regionId,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long userId = currentUserProvider.getCurrentUserId();

        MissionResDTO.MyMissionListDTO response = missionService.getMyMissions(
                userId,
                regionId,
                status,
                cursor,
                size
        );

        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_MISSION_LIST_SUCCESS, response);
    }

    @PatchMapping("/{userMissionId}")
    public ApiResponse<MissionResDTO.StartMissionResultDTO> startMission(
            @PathVariable Long userMissionId
    ) {
        Long userId = currentUserProvider.getCurrentUserId();

        MissionResDTO.StartMissionResultDTO response = missionService.startMission(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.START_MISSION_SUCCESS, response);
    }

    @PatchMapping("/{userMissionId}/cancel")
    public ApiResponse<MissionResDTO.CancelMissionResultDTO> cancelMission(
            @PathVariable Long userMissionId
    ) {
        Long userId = currentUserProvider.getCurrentUserId();

        MissionResDTO.CancelMissionResultDTO response = missionService.cancelMission(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.CANCEL_MISSION_SUCCESS, response);
    }

    @PostMapping("/{userMissionId}/verification/request")
    public ApiResponse<MissionResDTO.VerificationRequestResultDTO> requestMissionVerification(
            @PathVariable Long userMissionId
    ) {
        Long userId = currentUserProvider.getCurrentUserId();

        MissionResDTO.VerificationRequestResultDTO response =
                missionService.requestMissionVerification(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.REQUEST_MISSION_VERIFICATION_SUCCESS, response);
    }

    @PostMapping("/{userMissionId}/verification/confirm")
    public ApiResponse<MissionResDTO.VerificationConfirmResultDTO> confirmMissionVerification(
            @PathVariable Long userMissionId
    ) {
        Long userId = currentUserProvider.getCurrentUserId();

        MissionResDTO.VerificationConfirmResultDTO response =
                missionService.confirmMissionVerification(userId, userMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.CONFIRM_MISSION_VERIFICATION_SUCCESS, response);
    }
}
