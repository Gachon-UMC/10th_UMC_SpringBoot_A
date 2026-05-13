package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/missions")
    public ApiResponse<Page<MissionResDTO.MissionInfo>> myMission(
            @RequestParam Status status,
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<MissionResDTO.MissionInfo> response =
                missionService.getMyMissions(1L, status, PageRequest.of(page, size));

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_OK, response);
    }
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeDTO> home(
            @RequestParam Long memberId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        MissionResDTO.HomeDTO response = missionService.getHome(memberId, PageRequest.of(page, size));
        return ApiResponse.onSuccess(MissionSuccessCode.HOME_OK, response);
    }
}
