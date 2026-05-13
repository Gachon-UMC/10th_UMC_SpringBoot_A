package com.example.umc10th.domain.term.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode {

    TERM_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "TERM404_1",
            "약관을 찾을 수 없습니다."
    ),
    INVALID_TERM_TYPE(
            HttpStatus.BAD_REQUEST,
            "TERM400_1",
            "약관 타입이 올바르지 않습니다."
    ),
    REQUIRED_TERM_NOT_AGREED(
            HttpStatus.BAD_REQUEST,
            "TERM400_2",
            "필수 약관에 동의해야 합니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}