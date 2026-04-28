package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_CREATE_SUCCESS(HttpStatus.OK, "MISSION200", "미션 생성에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}
