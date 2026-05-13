package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<UserResDTO.MyInfoDTO> getMyInfo() {
        Long userId = 1L;

        UserResDTO.MyInfoDTO response = userService.getMyInfo(userId);

        return ApiResponse.onSuccess(UserSuccessCode.GET_MY_INFO_SUCCESS, response);
    }

    // 내 정보 수정
    @PatchMapping("/me")
    public ApiResponse<UserResDTO.UpdateMyInfoResultDTO> updateMyInfo(
            @RequestBody UserReqDTO.UpdateMyInfoDTO request
    ) {
        UserResDTO.UpdateMyInfoResultDTO response = UserResDTO.UpdateMyInfoResultDTO.builder()
                .userId(1L)
                .nickname(request.nickname())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .profileImageUrl(request.profileImageUrl())
                .updatedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.UPDATE_MY_INFO_SUCCESS, response);
    }

    @DeleteMapping("/me")
    public ApiResponse<UserResDTO.DeleteUserResultDTO> deleteMyAccount() {
        UserResDTO.DeleteUserResultDTO response = UserResDTO.DeleteUserResultDTO.builder()
                .userId(1L)
                .deletedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.DELETE_USER_SUCCESS, response);
    }

    @GetMapping("/me/points")
    public ApiResponse<UserResDTO.PointDTO> getMyPoint() {
        UserResDTO.PointDTO response = UserResDTO.PointDTO.builder()
                .point(2500)
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.GET_POINT_SUCCESS, response);
    }

    @GetMapping("/me/notification-settings")
    public ApiResponse<UserResDTO.NotificationSettingDTO> getNotificationSetting() {
        UserResDTO.NotificationSettingDTO response = UserResDTO.NotificationSettingDTO.builder()
                .reviewReplyNotification(true)
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.GET_NOTIFICATION_SETTING_SUCCESS, response);
    }

    @PatchMapping("/me/notification-settings")
    public ApiResponse<UserResDTO.NotificationSettingDTO> updateNotificationSetting(
            @RequestBody UserReqDTO.UpdateNotificationSettingDTO request
    ) {
        UserResDTO.NotificationSettingDTO response = UserResDTO.NotificationSettingDTO.builder()
                .reviewReplyNotification(request.reviewReplyNotification())
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.UPDATE_NOTIFICATION_SETTING_SUCCESS, response);
    }

}