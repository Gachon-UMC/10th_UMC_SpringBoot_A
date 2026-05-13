package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    RESPONSE_ID_BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "REVIEW400",
            "리뷰 답글을 찾을 수 없습니다"),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰 호출을 실패했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
