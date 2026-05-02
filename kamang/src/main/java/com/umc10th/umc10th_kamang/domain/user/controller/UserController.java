package com.umc10th.umc10th_kamang.domain.user.controller;

import com.umc10th.umc10th_kamang.domain.user.dto.UserRequest;
import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    /**
     * 약관 목록 조회
     * GET /api/auth/terms
     */
    @GetMapping("/terms")
    public ApiResponse<UserResponse.TermsListDTO> getTerms() {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }

    /**
     * 회원가입
     * POST /api/auth/signup
     */
    @PostMapping("/signup")
    public ApiResponse<UserResponse.SignupResultDTO> signup(@RequestBody UserRequest.SignupDTO request) {
        // TODO: 다음 주차에서 Service 연결 예정
        return ApiResponse.onSuccess(null);
    }
}
