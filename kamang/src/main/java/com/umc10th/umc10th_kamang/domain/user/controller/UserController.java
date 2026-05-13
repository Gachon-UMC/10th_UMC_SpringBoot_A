package com.umc10th.umc10th_kamang.domain.user.controller;

import com.umc10th.umc10th_kamang.domain.user.dto.UserRequest;
import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.domain.user.service.UserService;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 및 인증 관련 API")
public class UserController {

    private final UserService userService;

    /**
     * 약관 목록 조회
     * GET /api/auth/terms
     */
    @GetMapping("/api/auth/terms")
    public ApiResponse<UserResponse.TermsListDTO> getTerms() {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }

    /**
     * 회원가입
     * POST /api/auth/signup
     */
    @PostMapping("/api/auth/signup")
    public ApiResponse<UserResponse.SignupResultDTO> signup(@RequestBody UserRequest.SignupDTO request) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }

    /**
     * 마이페이지 조회
     * GET /api/users/me
     */
    @GetMapping("/api/users/me")
    @Operation(
            summary = "마이페이지 조회",
            description = "마이페이지에 표시할 사용자 기본 정보와 포인트를 조회합니다. 인증 구현 전까지 userId를 query parameter로 전달합니다."
    )
    public ApiResponse<UserResponse.MyPageDTO> getMyPage(
            @Parameter(description = "임시 사용자 ID", example = "1", required = true)
            @RequestParam Long userId) {
        return ApiResponse.onSuccess(userService.getMyPage(userId));
    }
}
