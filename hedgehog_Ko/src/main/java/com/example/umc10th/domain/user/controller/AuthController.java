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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthControllerDocs {

    private final UserService userService;

    @Override
    @PostMapping("/signup")
    public ApiResponse<UserResDTO.SignupResultDTO> signup(@RequestBody UserReqDTO.SignupDTO user) {
        UserResDTO.SignupResultDTO response = userService.signup(user);

        return ApiResponse.onSuccess(UserSuccessCode.SIGNUP_SUCCESS, response);
    }
}
