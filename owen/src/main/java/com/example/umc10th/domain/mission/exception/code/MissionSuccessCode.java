package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    LIST_OK(HttpStatus.OK, "MISSION200_1", "성공적으로 미션 목록을 조회했습니다."),
    CHALLENGE_OK(HttpStatus.OK, "MISSION200_2", "성공적으로 미션에 도전했습니다."),
    COMPLETE_REQUEST_OK(HttpStatus.OK, "MISSION200_3", "성공적으로 미션 성공을 요청했습니다."),
    VERIFY_OK(HttpStatus.OK, "MISSION200_4", "성공적으로 미션 성공을 인증했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}