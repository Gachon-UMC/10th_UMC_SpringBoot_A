package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.ObjectReadContext;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MissionController {

    private final MissionService missionService;


    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.GetMission> createMission(
            @PathVariable Long storeId,
            @RequestParam @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId,dto));
    }

//   가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code =MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    @GetMapping("/my/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionInfo>> getMyMissions(
            @RequestBody Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort){

        Sort sortInfo = sort != null ? Sort.by(sort) : Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(memberId,Status.IN_PROGRESS,pageRequest));
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
