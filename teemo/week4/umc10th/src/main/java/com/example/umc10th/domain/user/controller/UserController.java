package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final MissionService missionService;

    @GetMapping("/users/{userId}/mypage")
    @Operation(summary = "마이페이지 조회 API", description = "사용자의 마이페이지 정보를 조회하는 API입니다.")
    public ApiResponse<UserResDTO.MyPageDTO> getMyPage(@PathVariable Long userId) {
        User user = userService.getMyPage(userId);
        return ApiResponse.onSuccess(UserConverter.toMyPageDTO(user));
    }

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

    @GetMapping("/users/{userId}/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중(is_completed=false) 또는 완료(is_completed=true)된 미션 목록을 조회합니다.")
    @Parameter(name = "is_completed", description = "완료 여부 (true: 완료, false: 진행 중)")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(name = "is_completed") Boolean isCompleted,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        var userMissionPage = missionService.getMyMissionList(userId, isCompleted, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(userMissionPage));
    }

}
