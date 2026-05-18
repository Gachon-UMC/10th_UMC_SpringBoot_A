package com.umc10th.umc10th_kamang.domain.user.controller;

import com.umc10th.umc10th_kamang.domain.user.dto.UserRequest;
import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "사용자 및 인증 관련 API")
public interface UserApi {

    @Operation(
            summary = "약관 목록 조회",
            description = "회원가입 화면에서 표시할 약관 목록을 조회합니다. `/api/auth/**` Public API에 포함되어 로그인 없이 호출할 수 있습니다."
    )
    ApiResponse<UserResponse.TermsListDTO> getTerms();

    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀번호를 포함한 사용자 기본 정보, 약관 동의, 선호 음식 카테고리를 저장합니다. 비밀번호는 BCrypt로 암호화되어 저장됩니다."
    )
    ApiResponse<UserResponse.SignupResultDTO> signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "회원가입 요청 정보",
                    required = true
            )
            UserRequest.SignupDTO request);

    @Operation(
            summary = "마이페이지 조회",
            description = "마이페이지에 표시할 사용자 기본 정보와 보유 포인트를 조회합니다. Private API이므로 로그인 후 호출해야 합니다. 현재는 userId를 query parameter로 전달합니다."
    )
    ApiResponse<UserResponse.MyPageDTO> getMyPage(
            @Parameter(name = "userId", description = "조회할 사용자 ID", example = "1", required = true)
            Long userId);
}
