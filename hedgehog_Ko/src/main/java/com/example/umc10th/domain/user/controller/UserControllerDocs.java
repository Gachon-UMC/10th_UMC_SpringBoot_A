package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "내 정보, 포인트, 알림 설정 관련 API")
public interface UserControllerDocs {

    @Operation(
            summary = "내 정보 조회",
            description = "현재 사용자의 이름, 닉네임, 주소, 프로필 이미지 등 기본 정보를 조회합니다."
    )
    ApiResponse<UserResDTO.MyInfoDTO> getMyInfo();

    @Operation(
            summary = "내 정보 수정",
            description = "현재 사용자의 닉네임, 주소, 상세 주소, 프로필 이미지 정보를 수정합니다."
    )
    ApiResponse<UserResDTO.UpdateMyInfoResultDTO> updateMyInfo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "내 정보 수정 요청 정보",
                    required = true
            )
            UserReqDTO.UpdateMyInfoDTO request
    );

    @Operation(
            summary = "회원 탈퇴",
            description = "현재 사용자의 계정을 탈퇴 처리합니다."
    )
    ApiResponse<UserResDTO.DeleteUserResultDTO> deleteMyAccount();

    @Operation(
            summary = "내 포인트 조회",
            description = "현재 사용자가 보유한 포인트를 조회합니다."
    )
    ApiResponse<UserResDTO.PointDTO> getMyPoint();

    @Operation(
            summary = "알림 설정 조회",
            description = "현재 사용자의 리뷰 답글 알림 수신 설정을 조회합니다."
    )
    ApiResponse<UserResDTO.NotificationSettingDTO> getNotificationSetting();

    @Operation(
            summary = "알림 설정 수정",
            description = "현재 사용자의 리뷰 답글 알림 수신 여부를 수정합니다."
    )
    ApiResponse<UserResDTO.NotificationSettingDTO> updateNotificationSetting(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "알림 설정 수정 요청 정보",
                    required = true
            )
            UserReqDTO.UpdateNotificationSettingDTO request
    );
}
