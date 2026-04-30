package com.example.umc10th.global.apiPayload.code;

//import lombok.Getter;
//import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;

//@Getter //`@Getter`가 없다면, 일일이 getter 메소드를 만들어줘야 한다!! 한 번 만들어보자!
//@RequiredArgsConstructor // 이 `@RequiredArgsConstructor`를 사용하지 않으면, 생성자를 직접 만들어줘야 한다. 한 번 만들어보자!
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "COMMON200_1",
            "성공적으로 요청을 처리했습니다."
    ),
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "성공적으로 요청을 처리하고 리소스를 만들었습니다."
    ); // enum은 상수 목록이 끝난 뒤에 필드나 메소드를 작성하기 위해서는 `;`를 적어야 한다.

    private final HttpStatus status;
    private final String code;
    private final String message;

    GeneralSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
