package com.umc10th.umc10th_kamang.domain.user.controller;

import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "사용자 및 인증 관련 API")
public interface UserApi {

    @Operation(
            summary = "마이페이지 조회",
            description = "마이페이지에 표시할 사용자 기본 정보와 포인트를 조회합니다. 인증 구현 전까지 userId를 query parameter로 전달합니다."
    )
    ApiResponse<UserResponse.MyPageDTO> getMyPage(
            @Parameter(name = "userId", description = "임시 사용자 ID", example = "1", required = true)
            Long userId);
}
