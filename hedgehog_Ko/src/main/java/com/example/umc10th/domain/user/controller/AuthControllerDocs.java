package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "회원가입 등 인증 진입 API")
public interface AuthControllerDocs {

    @Operation(
            summary = "회원가입",
            description = "사용자 기본 정보, 소셜 로그인 정보, 약관 동의 정보, 선호 음식 카테고리를 받아 회원가입을 처리합니다."
    )
    ApiResponse<UserResDTO.SignupResultDTO> signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "회원가입 요청 정보",
                    required = true
            )
            UserReqDTO.SignupDTO user
    );
}
