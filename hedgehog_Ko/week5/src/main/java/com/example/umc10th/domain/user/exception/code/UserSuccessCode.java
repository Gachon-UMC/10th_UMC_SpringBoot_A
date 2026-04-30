package com.example.umc10th.domain.user.exception.code;


import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {
    SIGNUP_SUCCESS(HttpStatus.CREATED,
            "USER201_1",
            "회원가입에 성공했습니다.."),
    GET_MY_INFO_SUCCESS(HttpStatus.OK,
            "USER200_1",
            "내 정보 조회에 성공했습니다."),
    UPDATE_MY_INFO_SUCCESS(HttpStatus.OK,
            "USER200_2",
            "내 정보 수정에 성공했습니다."),
    DELETE_USER_SUCCESS(HttpStatus.OK,
            "USER200_3",
            "계정 탈퇴에 성공했습니다."),
    GET_POINT_SUCCESS(HttpStatus.OK,
            "USER200_4",
            "내 포인트 조회에 성공했습니다."),
    GET_NOTIFICATION_SETTING_SUCCESS(HttpStatus.OK,
            "USER200_5",
            "내 알림 설정 조회에 성공했습니다."),
    UPDATE_NOTIFICATION_SETTING_SUCCESS(HttpStatus.OK,
            "USER200_6",
            "내 알림 설정 변경에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
