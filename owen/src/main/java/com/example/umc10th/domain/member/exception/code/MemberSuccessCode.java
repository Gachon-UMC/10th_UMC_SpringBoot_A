package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    HOME_OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 홈 정보를 조회했습니다."),
    MYPAGE_OK(HttpStatus.OK, "MEMBER200_2", "성공적으로 마이페이지 정보를 조회했습니다."),
    UPDATE_OK(HttpStatus.OK, "MEMBER200_3", "성공적으로 내 정보를 수정했습니다."),
    WITHDRAW_OK(HttpStatus.OK, "MEMBER200_4", "성공적으로 계정을 탈퇴했습니다."),
    POINT_OK(HttpStatus.OK, "MEMBER200_5", "성공적으로 포인트를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}