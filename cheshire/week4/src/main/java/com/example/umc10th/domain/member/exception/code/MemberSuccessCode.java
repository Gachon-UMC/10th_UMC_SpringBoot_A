package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    SIGN_UP_CREATED(HttpStatus.CREATED,
            "MEMBER201_1",
            "성공적으로 회원가입이 완료되었습니다."),
    SIGN_IN_OK(HttpStatus.OK,
            "MEMBER200_2",
            "성공적으로 로그인이 완료되었습니다."),
    MEMBER_UPDATE_OK(HttpStatus.OK,
            "MEMBER200_3",
            "성공적으로 회원정보 수정이 완료되었습니다."),

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
