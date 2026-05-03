package com.example.umc10th.global.apiPayload.code;
// 성공 응답 코드를 표준화하기 위한 인터페이스 파일(**성공 코드의 공통 규칙 Interface!!**)

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
/*
    성공 응답 코드가 다음과 같도록 함.
    {
       "isSuccess": true,
       "code": "COMMON200",
       "message": "성공적으로 요청을 처리했습니다."
    }
 */