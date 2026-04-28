package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 진행 중인 미션입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션 호출을 실패했습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "식당 호출을 실패했습니다."),
    MISSION_DUE_DATE_PASSED(HttpStatus.GONE,
            "MISSION410_1",
            "미션 진행 기간이 지났습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
