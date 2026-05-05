package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면, 진행중, 진행 완료 미션 목록 모두 사용 가능.
    @GetMapping("/me/mission")
    public Response<MissionResponseDTO.MissionListDTO> getMemberMissions(
        @RequestParam Long userId,
        @RequestParam(required = false) Boolean isCompleted,
        @RequestParam(required = false) Long cursor
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMemberMissions(userId, isCompleted, cursor));
    }

    @GetMapping("/missions/count/region/{id}")
    public Response<MissionResponseDTO.MissionStatsDTO> getMissionCountByRegion(
        @RequestParam Long userId,
        @PathVariable Long id
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.getMissionCountByRegion(userId, id));
    }

    @PostMapping("/missions/{id}/challenge")
    public Response<MissionResponseDTO.MissionChallengeResultDTO> createMissionChallenge(@RequestParam Long userId, @PathVariable Long id) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return Response.onSuccess(code, missionService.createMissionChallenge(userId, id));
    }

    @PostMapping("/missions/{id}/complete")
    public Response<String> updateMissionCompletion(@PathVariable Long id) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        missionService.updateMissionCompletion(id);
        return Response.onSuccess(code, "미션 완료 성공");
    }
}
