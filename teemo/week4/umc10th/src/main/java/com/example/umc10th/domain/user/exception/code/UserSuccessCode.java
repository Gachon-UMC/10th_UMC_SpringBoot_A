package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),
    JOIN_SUCCESS(HttpStatus.CREATED, "MEMBER201", "회원가입에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
