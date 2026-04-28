package com.example.umc10th.global.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public String getCode() {
        return this.code.getCode();
    }

    public String getMessage() {
        return this.code.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return this.code.getHttpStatus();
    }
}
