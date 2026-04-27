package com.umc10th.umc10th_kamang.domain.user.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "사용자를 찾을 수 없습니다."),
    TERMS_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4002", "약관을 찾을 수 없습니다."),
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "USER4003", "필수 약관에 동의해야 합니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "USER4091", "이미 사용 중인 이메일입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
