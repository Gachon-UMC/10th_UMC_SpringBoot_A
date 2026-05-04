package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_SUCCESS(HttpStatus.OK, "REVIEW200", "리뷰 작성에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}
