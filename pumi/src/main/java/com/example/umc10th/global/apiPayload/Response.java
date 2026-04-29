package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class Response<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)
    public static <T> Response<T> onSuccess(T result) {
        return new Response<>(true, GeneralSuccessCode.OK.getCode(), GeneralSuccessCode.OK.getMessage(), result);
    }

    public static <T> Response<T> onSuccess(BaseSuccessCode code, T result) {
        return new Response<>(true, code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우 (result 포함)
    public static <T> Response<T> onFailure(BaseErrorCode code, T result) {
        return new Response<>(false, code.getCode(), code.getMessage(), result);
    }
}
