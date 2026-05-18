package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/me/mission")
    public Response<MissionResponseDTO.MissionListDTO> getUserMissions(
            @AuthenticationPrincipal AuthMember authMember,
            @Valid @RequestBody MissionRequestDTO.UserMissionSearchRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getUserMissions(
                authMember.getUserId(),
                request.isCompleted(),
                request.regionId(),
                request.cursor()
        ));
    }

    @PostMapping("/missions/ongoing")
    public Response<MissionResponseDTO.OngoingMissionListDTO> getOngoingMissions(
            @AuthenticationPrincipal AuthMember authMember,
            @Valid @RequestBody MissionRequestDTO.OngoingMissionRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getOngoingMissions(
                authMember.getUserId(),
                request.page(),
                request.size()
        ));
    }

    @PostMapping("/missions/count/region/{id}")
    public Response<MissionResponseDTO.MissionStatsDTO> getMissionCountByRegion(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMissionCountByRegion(authMember.getUserId(), id));
    }

    @PostMapping("/missions/{id}/challenge")
    public Response<MissionResponseDTO.MissionChallengeResultDTO> createMissionChallenge(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.createMissionChallenge(authMember.getUserId(), id));
    }

    @PostMapping("/missions/{userMissionId}/complete")
    public Response<String> updateMissionCompletion(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long userMissionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        missionService.updateMissionCompletion(authMember.getUserId(), userMissionId);
        return Response.onSuccess(code, "미션 완료 성공");
    }
}
