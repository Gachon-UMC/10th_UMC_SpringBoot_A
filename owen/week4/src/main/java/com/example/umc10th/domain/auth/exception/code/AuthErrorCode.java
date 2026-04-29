package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "AUTH409_1", "이미 사용 중인 이메일입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_1", "이메일 또는 비밀번호가 일치하지 않습니다."),
    REQUIRED_TERMS_NOT_AGREED(HttpStatus.BAD_REQUEST, "AUTH400_1", "필수 약관에 모두 동의해야 합니다."),
    INVALID_PREFERENCE(HttpStatus.BAD_REQUEST, "AUTH400_2", "유효하지 않은 선호 카테고리입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}