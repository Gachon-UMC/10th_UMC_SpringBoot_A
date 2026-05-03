package com.example.umc10th.global.apiPayload.code;
// 에러 응답 코드를 표준화하기 위한 인터페이스 파일 (**에러 코드의 공통 규칙 Interface!!**)

import org.springframework.http.HttpStatus; // Spring에서 제공하는 HttpStatus 상태 코드 enum이 있는 파일을 import해서 사용할 수 있도록 한다.

public interface BaseErrorCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
/*
Error Code에 대한 Class나 enum이 반드시 지켜야 하는 3가지 메소드를 적어둠.
    1. HttpStatus getStatus() -> HTTP Status Code를 반환하라!
    2. 상속한 Class에서 정한 에러 코드 문자열을 반환하라.
    3. 상속한 Class에서 정한 에러 코드에 대한 설명을 반환하라.
    그래서 이런 식의 응답이 나오도록 한다.
    {
       "isSuccess": false,
       "code": "COMMON400",
       "message": "잘못된 요청입니다."
    }
*/