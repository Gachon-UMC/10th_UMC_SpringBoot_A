package com.example.umc10th.domain.mission.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MY_MISSION_LIST_SUCCESS(HttpStatus.OK,
            "MISSION200_1",
            "내 미션 목록 조회에 성공했습니다."),
    START_MISSION_SUCCESS(HttpStatus.OK,
            "MISSION200_2",
            "미션 도전에 성공했습니다."),
    CANCEL_MISSION_SUCCESS(HttpStatus.OK,
            "MISSION200_3",
            "미션 취소에 성공했습니다."),
    REQUEST_MISSION_VERIFICATION_SUCCESS(HttpStatus.OK,
            "MISSION200_4",
            "미션 성공 인증 요청에 성공했습니다."),
    CONFIRM_MISSION_VERIFICATION_SUCCESS(HttpStatus.OK,
            "MISSION200_5",
            "미션 성공 인증 확인에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}