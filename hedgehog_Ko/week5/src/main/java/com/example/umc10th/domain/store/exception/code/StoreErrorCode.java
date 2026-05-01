package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "가게를 찾을 수 없습니다."),
    INVALID_STORE_REQUEST(HttpStatus.BAD_REQUEST,
            "STORE400_1",
            "가게 요청 값이 올바르지 않습니다."),
    INVALID_STORE_CURSOR(HttpStatus.BAD_REQUEST,
            "STORE400_2",
            "커서 값이 올바르지 않습니다."),
    INVALID_STORE_CATEGORY(HttpStatus.BAD_REQUEST,
            "STORE400_3",
            "가게 카테고리 값이 올바르지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
