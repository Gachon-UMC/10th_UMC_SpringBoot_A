package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰 호출을 실패했습니다."),
    RESPONSE_ID_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_2",
            "답글 ID가 잘못되었습니다."), //메시지를 어떻게 달아야할지 잘 모르겠어요ㅠ
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
