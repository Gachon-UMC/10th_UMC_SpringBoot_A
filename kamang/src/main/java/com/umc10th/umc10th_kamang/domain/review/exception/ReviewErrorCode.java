package com.umc10th.umc10th_kamang.domain.review.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 찾을 수 없습니다."),
    INVALID_SCORE(HttpStatus.BAD_REQUEST, "REVIEW4002", "별점은 1.0 이상 5.0 이하로 입력해야 합니다."),
    REVIEW_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "REVIEW4003", "리뷰를 작성할 수 있는 완료 미션이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
