package com.example.umc10th.global.apiPayload;
// API 응답 형식을 통일하기 위한 공통 응답 객체 파일이다.

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import lombok.AccessLevel; // AccessLevel을 사용하기 위한 어노테이션
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //(access = AccessLevel.PRIVATE) // 생성자가 private이 되도록 하는 코드이다. 넣는 게 좋다고 한다! 외부에서 new로 직접 만드는 것을 방지!!
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) // JSON 형식(직렬화 순서) 지정하는 코드
public class ApiResponse<T> {

    @JsonProperty("isSuccess") // JSON 매핑할때 필드명을 지정 "isSuccess: true" 처럼 됨.
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private final T result; // result에 어떤 게 들어올지 모르기 때문에 Generic 타입으로 정리함.ㄹ

    // 성공한 경우 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}