package com.example.umc10th.domain.term.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermSuccessCode implements BaseSuccessCode {

    GET_TERM_LIST_SUCCESS(
            HttpStatus.OK,
            "TERM200_1",
            "약관 목록 조회에 성공했습니다."
    ),
    GET_TERM_DETAIL_SUCCESS(
            HttpStatus.OK,
            "TERM200_2",
            "약관 상세 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}