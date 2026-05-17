package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @Override
    @GetMapping("/me")
    public ApiResponse<UserResDTO.MyInfoDTO> getMyInfo() {
        Long userId = 1L;

        UserResDTO.MyInfoDTO response = userService.getMyInfo(userId);

        return ApiResponse.onSuccess(UserSuccessCode.GET_MY_INFO_SUCCESS, response);
    }

    @Override
    @PatchMapping("/me")
    public ApiResponse<UserResDTO.UpdateMyInfoResultDTO> updateMyInfo(
            @RequestBody UserReqDTO.UpdateMyInfoDTO request
    ) {
        Long userId = 1L;

        UserResDTO.UpdateMyInfoResultDTO response = userService.updateMyInfo(userId, request);

        return ApiResponse.onSuccess(UserSuccessCode.UPDATE_MY_INFO_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/me")
    public ApiResponse<UserResDTO.DeleteUserResultDTO> deleteMyAccount() {
        Long userId = 1L;

        UserResDTO.DeleteUserResultDTO response = userService.deleteMyAccount(userId);

        return ApiResponse.onSuccess(UserSuccessCode.DELETE_USER_SUCCESS, response);
    }

    @Override
    @GetMapping("/me/points")
    public ApiResponse<UserResDTO.PointDTO> getMyPoint() {
        Long userId = 1L;

        UserResDTO.PointDTO response = userService.getMyPoint(userId);

        return ApiResponse.onSuccess(UserSuccessCode.GET_POINT_SUCCESS, response);
    }

    @Override
    @GetMapping("/me/notification-settings")
    public ApiResponse<UserResDTO.NotificationSettingDTO> getNotificationSetting() {
        UserResDTO.NotificationSettingDTO response = UserResDTO.NotificationSettingDTO.builder()
                .reviewReplyNotification(true)
                .build();

        return ApiResponse.onSuccess(UserSuccessCode.GET_NOTIFICATION_SETTING_SUCCESS, response);
    }

    @Override
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
