package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 회원이 존재하지 않습니다."),
    MEMBER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER409", "이미 사용 중인 이메일입니다."),
    MEMBER_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "MEMBER401", "이메일 또는 비밀번호가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
