package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_OK(HttpStatus.CREATED, "REVIEW201_1", "성공적으로 리뷰를 작성했습니다."),
    LIST_OK(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰 목록을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}