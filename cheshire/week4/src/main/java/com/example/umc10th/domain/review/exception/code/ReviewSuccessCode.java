package com.example.umc10th.domain.review.exception.code;


import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode{
    REVIEW_LIST_OK(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰 호출이 정상적으로 처리되었습니다."),
    REVIEW_REGISTER_CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰 등록이 정상적으로 처리되었습니다."),
    RESPONSE_REGISTER_CREATED(HttpStatus.CREATED,
            "REVIEW201_2",
            "리뷰 답글 등록이 정상적으로 처리되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
