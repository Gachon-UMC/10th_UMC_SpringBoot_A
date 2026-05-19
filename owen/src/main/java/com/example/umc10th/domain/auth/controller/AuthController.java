package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth-controller", description = "회원가입 (Public API)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // [8주차] 회원가입: POST /auth/signup
    @Operation(summary = "회원가입", description = "약관 동의 + 회원 정보 + 선호 음식 등록. BCrypt 솔트로 비밀번호 저장.")
    @PostMapping("/signup")
    public ApiResponse<AuthResDTO.SignUp> signUp(
            @Valid @RequestBody AuthReqDTO.SignUp dto
    ) {
        AuthResDTO.SignUp result = authService.signUp(dto);
        return ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_OK, result);
    }

    // 사용자 선호 카테고리: POST /auth/preferences
    @PostMapping("/preferences")
    public ApiResponse<AuthResDTO.Preference> registerPreference(
            @RequestBody AuthReqDTO.Preference dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.PREFERENCE_OK, null);
    }

    // 약관 동의 처리: POST /auth/terms
    @PostMapping("/terms")
    public ApiResponse<AuthResDTO.Terms> agreeTerms(
            @RequestBody AuthReqDTO.Terms dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.TERMS_OK, null);
    }
}
