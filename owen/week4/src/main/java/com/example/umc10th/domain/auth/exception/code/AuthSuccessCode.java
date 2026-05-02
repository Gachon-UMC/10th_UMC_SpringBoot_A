package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    SIGNUP_OK(HttpStatus.CREATED, "AUTH201_1", "회원가입에 성공했습니다."),
    LOGIN_OK(HttpStatus.OK, "AUTH200_1", "로그인에 성공했습니다."),
    PREFERENCE_OK(HttpStatus.OK, "AUTH200_2", "선호 카테고리가 등록되었습니다."),
    TERMS_OK(HttpStatus.OK, "AUTH200_3", "약관 동의가 처리되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}