package com.umc10th.umc10th_kamang.domain.mission.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션을 찾을 수 없습니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4002", "사용자 미션을 찾을 수 없습니다."),
    MISSION_STATUS_INVALID(HttpStatus.BAD_REQUEST, "MISSION4003", "유효하지 않은 미션 상태 변경입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
