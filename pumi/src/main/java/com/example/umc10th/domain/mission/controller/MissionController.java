package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
            @Valid @RequestBody MissionRequestDTO.UserMissionSearchRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getUserMissions(
                request.userId(),
                request.isCompleted(),
                request.regionId(),
                request.cursor()
        ));
    }

    @PostMapping("/missions/ongoing")
    public Response<MissionResponseDTO.OngoingMissionListDTO> getOngoingMissions(
            @Valid @RequestBody MissionRequestDTO.OngoingMissionRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getOngoingMissions(
                request.userId(),
                request.page(),
                request.size()
        ));
    }

    @PostMapping("/missions/count/region/{id}")
    public Response<MissionResponseDTO.MissionStatsDTO> getMissionCountByRegion(
            @PathVariable Long id,
            @Valid @RequestBody MissionRequestDTO.UserIdRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMissionCountByRegion(request.userId(), id));
    }

    @PostMapping("/missions/{id}/challenge")
    public Response<MissionResponseDTO.MissionChallengeResultDTO> createMissionChallenge(
            @PathVariable Long id,
            @Valid @RequestBody MissionRequestDTO.UserIdRequestDTO request
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.createMissionChallenge(request.userId(), id));
    }

    @PostMapping("/missions/{userMissionId}/complete")
    public Response<String> updateMissionCompletion(@PathVariable Long userMissionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        missionService.updateMissionCompletion(userMissionId);
        return Response.onSuccess(code, "미션 완료 성공");
    }
}
