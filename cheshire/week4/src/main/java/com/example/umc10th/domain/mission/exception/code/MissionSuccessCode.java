package com.example.umc10th.domain.mission.exception.code;


import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."),
    MISSION_CHALLENGE_OK(HttpStatus.OK,
            "MISSION200_2",
            "미션 도전 요청을 성공적으로 처리했습니다."),
    MISSION_COMPLETE_OK(HttpStatus.OK,
            "MISSION200_3",
            "미션 성공 처리를 성공적으로 처리했습니다."),
    MISSION_LIST_OK(HttpStatus.OK,
            "MISSION200_4",
            "성공적으로 미션을 조회했습니다."),
    HOME_OK(HttpStatus.OK,
            "MISSION200_5",
                    "홈 화면 호출을 성공적으로 처리했습니다."),

            ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
