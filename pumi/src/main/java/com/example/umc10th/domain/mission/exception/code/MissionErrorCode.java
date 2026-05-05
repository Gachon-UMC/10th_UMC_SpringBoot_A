package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "해당 미션이 존재하지 않습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION404", "해당 지역이 존재하지 않습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION404", "해당 회원 미션이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
