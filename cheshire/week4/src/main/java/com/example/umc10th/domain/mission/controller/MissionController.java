package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeDTO> home(){
        MissionResDTO.HomeDTO response = MissionResDTO.HomeDTO.builder()
                .lacationName("수정구")
                .totalPoint(1000)
                .missions(List.of())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.HOME_OK, response);
    }

    @GetMapping("my/missions")
    public ApiResponse<List<MissionResDTO.MissionInfo>> myMission(){
        List<MissionResDTO.MissionInfo> response  = List.of(
                MissionResDTO.MissionInfo.builder()
                        .storeName("맥도날드")
                        .storeCategory("패스트푸드")
                        .content("버거 먹기")
                        .point(100)
                        .build()
        );

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_OK, response);

    }

    @PatchMapping("/my/missions/{userMissionId}/success")
    public ApiResponse<String> missionSuccess(
            @PathVariable Long userMissionId
    ){
        // TODO: service 연결 예정
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_OK, "미션 성공!");
    }

}
