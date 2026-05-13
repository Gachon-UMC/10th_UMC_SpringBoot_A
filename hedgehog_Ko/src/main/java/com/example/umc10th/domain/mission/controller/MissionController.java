package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/me/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(required = false) Long regionId,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long userId = 1L;

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
        MissionResDTO.StartMissionResultDTO response = MissionResDTO.StartMissionResultDTO.builder()
                .userMissionId(userMissionId)
                .status(MissionStatus.CHALLENGING)
                .startedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.START_MISSION_SUCCESS, response);
    }

    @PatchMapping("/{userMissionId}/cancel")
    public ApiResponse<MissionResDTO.CancelMissionResultDTO> cancelMission(
            @PathVariable Long userMissionId
    ) {
        MissionResDTO.CancelMissionResultDTO response = MissionResDTO.CancelMissionResultDTO.builder()
                .userMissionId(userMissionId)
                .status(MissionStatus.CANCELED)
                .canceledAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.CANCEL_MISSION_SUCCESS, response);
    }

    @PostMapping("/{userMissionId}/verification/request")
    public ApiResponse<MissionResDTO.VerificationRequestResultDTO> requestMissionVerification(
            @PathVariable Long userMissionId
    ) {
        MissionResDTO.VerificationRequestResultDTO response = MissionResDTO.VerificationRequestResultDTO.builder()
                .userMissionId(userMissionId)
                .verificationCode("482913")
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.REQUEST_MISSION_VERIFICATION_SUCCESS, response);
    }

    @PostMapping("/{userMissionId}/verification/confirm")
    public ApiResponse<MissionResDTO.VerificationConfirmResultDTO> confirmMissionVerification(
            @PathVariable Long userMissionId
    ) {
        MissionResDTO.VerificationConfirmResultDTO response = MissionResDTO.VerificationConfirmResultDTO.builder()
                .userMissionId(userMissionId)
                .status(MissionStatus.COMPLETED)
                .earnedPoint(500L)
                .totalPoint(2500)
                .completedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.CONFIRM_MISSION_VERIFICATION_SUCCESS, response);
    }
}
