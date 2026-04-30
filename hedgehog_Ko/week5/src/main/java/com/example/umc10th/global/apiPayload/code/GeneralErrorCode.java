package com.example.umc10th.global.apiPayload.code;
// 전역 에러 코드 enum 파일이다!

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
/*
    enum은 보통 이렇게 많이 사용한다.
    ```
    public enum Gender {
        MALE("M", "남성"),
        FEMALE("W", "여성");
    }
    ```
    위처럼 정의한다.
 */

@Getter // GeneralErrorCode의 필드인 status, code, message의 getter 메소드를 자동으로 만들어준다.
@RequiredArgsConstructor // final 필드를 초기화해주는 생성자를 자동으로 만들어준다. (이건 워크북에서도 엄청 많이 보였다!)
public enum GeneralErrorCode implements BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."
    ),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "COMMON401_1",
            "인증되지 않았습니다."
    ),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403_1",
            "접근이 금지되었습니다."
    ),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "해당 리소스를 찾을 수 없습니다."
    ),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500",
            "서버 내부 오류가 발생했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
