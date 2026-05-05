package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/points")
    public Response<MissionResponseDTO.PointInfoDTO> getPoints() {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getPoints());
    }

    @GetMapping("/missions")
    public Response<MissionResponseDTO.MissionListDTO> getMissions() {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMissions());
    }

    @GetMapping("/missions/stats/region")
    public Response<MissionResponseDTO.MissionStatsListDTO> getMissionStatsByRegion() {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMissionStatsByRegion());
    }

    @PostMapping("/missions/{missionId}/challenge")
    public Response<MissionResponseDTO.MissionChallengeResultDTO> challengeMission(@PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.challengeMission(missionId));
    }

    @PostMapping("/missions/{missionId}/success")
    public Response<String> successMission(@PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        missionService.completeMission(missionId);
        return Response.onSuccess(code, "미션 성공 처리 완료");
    }
}
