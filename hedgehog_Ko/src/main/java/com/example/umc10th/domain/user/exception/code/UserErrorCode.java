package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, // 409는 Conflict로, 서버의 현재 상태와 요청이 충돌했음을 나타낸다.
            "USER409_1",  // 왜 중복인 경우에는 충돌일까? 그 이유는 **요청 자체의 형식은 옳지만, 현재 서버의 리소스(DB)와는 충돌하기 때문이다.**
            "이미 존재하는 사용자입니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED,
            "USER401_1",
            "인증 정보가 없습니다."),
    INVALID_USER_REQUEST(HttpStatus.BAD_REQUEST,
            "USER400_1",
            "사용자 요청 값이 올바르지 않습니다."),
    INVALID_NOTIFICATION_SETTING(HttpStatus.BAD_REQUEST,
            "USER400_2",
            "알림 설정 값이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
