package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserReqDTO {

    // ReqDTO는 주로 Request Body가 있는 경우에만 적는다고 한다.
    // static class 방식 예시 (java 원래 문법)
    // 회원 가입
    @Getter
    @NoArgsConstructor
    public static class SignupDTO {
        private String name;
        private Gender gender;
        private LocalDate birth;
        private String address;
    }

    // record 방식
    // 내 정보 수정
    public record UpdateMyInfoDTO(
            String nickName,
            String phone
    ) {
    }

    // 내 알람 설정 수정
    public record UpdateNotificationSettingDTO(
            Boolean reviewReplyNotification
    ) {
    }

}