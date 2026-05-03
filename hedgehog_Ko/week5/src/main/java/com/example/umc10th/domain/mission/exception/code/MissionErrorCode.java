package com.example.umc10th.domain.mission.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션을 찾을 수 없습니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "사용자 미션을 찾을 수 없습니다."),
    FORBIDDEN_USER_MISSION(HttpStatus.FORBIDDEN,
            "MISSION403_1",
            "해당 사용자 미션에 접근할 권한이 없습니다."),
    ALREADY_CHALLENGING_MISSION(HttpStatus.CONFLICT,
            "MISSION409_1",
            "이미 도전 중인 미션입니다."),
    ALREADY_COMPLETED_MISSION(HttpStatus.CONFLICT,
            "MISSION409_2",
            "이미 완료한 미션입니다."),
    ALREADY_CANCELED_MISSION(HttpStatus.CONFLICT,
            "MISSION409_3",
            "이미 취소한 미션입니다."),
    EXPIRED_MISSION(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "기간이 만료된 미션입니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST,
            "MISSION400_2",
            "미션 상태 값이 올바르지 않습니다."),
    INVALID_MISSION_CURSOR(HttpStatus.BAD_REQUEST,
            "MISSION400_3",
            "커서 값이 올바르지 않습니다."),
    MISSION_VERIFICATION_NOT_REQUESTED(HttpStatus.BAD_REQUEST,
            "MISSION400_4",
            "미션 성공 인증 요청이 필요합니다."),
    MISSION_VERIFICATION_EXPIRED(HttpStatus.BAD_REQUEST,
            "MISSION400_5",
            "미션 성공 인증 시간이 만료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}