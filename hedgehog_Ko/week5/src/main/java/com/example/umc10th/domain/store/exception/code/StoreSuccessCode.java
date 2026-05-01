package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    GET_STORE_LIST_SUCCESS(HttpStatus.OK,
            "STORE200_1",
            "가게 목록 조회에 성공했습니다."),
    GET_STORE_DETAIL_SUCCESS(HttpStatus.OK,
            "STORE200_2",
            "가게 상세 조회에 성공했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}