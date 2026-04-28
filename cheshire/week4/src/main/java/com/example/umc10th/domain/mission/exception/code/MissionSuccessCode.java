package com.example.umc10th.domain.mission.exception.code;


import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_CHALLENGE_OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 도전 요청을 성공적으로 처리했습니다."),
    MISSION_COMPLETE_OK(HttpStatus.OK,
            "MISSION200_2",
            "미션 성공 처리를 성공적으로 처리했습니다."),
    MISSION_LIST_OK(HttpStatus.OK,
            "MISSION200_3",
            "미션 리스트 호출을 성공적으로 처리했습니다."),
    HOME_OK(HttpStatus.OK,
            "MISSION200_4",
                    "홈 화면 호출을 성공적으로 처리했습니다."),
            ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
