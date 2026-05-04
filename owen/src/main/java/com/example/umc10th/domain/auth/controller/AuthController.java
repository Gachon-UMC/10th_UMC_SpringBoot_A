package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // 회원가입: POST /auth/signup
    @PostMapping("/signup")
    public ApiResponse<AuthResDTO.SignUp> signUp(
            @RequestBody AuthReqDTO.SignUp dto
    ) {
        // Service 다음 주차에 구현
        return ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_OK, null);
    }

    // 로그인: POST /auth/login
    @PostMapping("/login")
    public ApiResponse<AuthResDTO.Login> login(
            @RequestBody AuthReqDTO.Login dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.LOGIN_OK, null);
    }

    // 사용자 선호 카테고리: POST /auth/preferences
    @PostMapping("/preferences")
    public ApiResponse<AuthResDTO.Preference> registerPreference(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AuthReqDTO.Preference dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.PREFERENCE_OK, null);
    }

    // 약관 동의 처리: POST /auth/terms
    @PostMapping("/terms")
    public ApiResponse<AuthResDTO.Terms> agreeTerms(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AuthReqDTO.Terms dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.TERMS_OK, null);
    }
}