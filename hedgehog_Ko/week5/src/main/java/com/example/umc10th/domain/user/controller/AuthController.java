package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ApiResponse<UserResDTO.SignupResultDTO> signup(@RequestBody UserReqDTO.signupDTO user) {
        UserResDTO.SignupResultDTO response = UserResDTO.SignupResultDTO.builder()
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.SIGNUP_SUCCESS, response);
    }
}
