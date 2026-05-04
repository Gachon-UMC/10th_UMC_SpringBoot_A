package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    INVALID_MEMBER_INFO(HttpStatus.BAD_REQUEST, "MEMBER400_1", "잘못된 사용자 정보입니다."),
    ALREADY_WITHDRAWN(HttpStatus.CONFLICT, "MEMBER409_1", "이미 탈퇴한 사용자입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}