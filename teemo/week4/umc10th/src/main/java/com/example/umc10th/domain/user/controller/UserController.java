package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/auth/terms")
    @Operation(summary = "약관 목록 조회 API", description = "회원가입 시 필요한 약관 목록을 조회하는 API입니다.")
    public ApiResponse<UserResDTO.TermListDTO> getTerms() {
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/auth/signup")
    @Operation(summary = "회원가입 API", description = "회원가입을 진행하는 API입니다.")
    public ApiResponse<UserResDTO.JoinResultDTO> join(@RequestBody @Valid UserReqDTO.JoinDTO request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/users/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중(is_completed=false) 또는 완료(is_completed=true)된 미션 목록을 조회합니다.")
    @Parameter(name = "is_completed", description = "완료 여부 (true: 완료, false: 진행 중)")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMyMissions(
            @RequestParam(name = "is_completed") Boolean isCompleted,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(null);
    }
}
