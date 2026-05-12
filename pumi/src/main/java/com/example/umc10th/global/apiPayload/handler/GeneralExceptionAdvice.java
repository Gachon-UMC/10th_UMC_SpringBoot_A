package com.example.umc10th.global.apiPayload.handler;

import com.example.umc10th.global.apiPayload.Exception.ProjectException;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<Response<Void>> handleMemberException(
            ProjectException e
    ) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(Response.onFailure(errorCode, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException e
    ) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(Response.onFailure(code, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(
            Exception ex
    ) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(Response.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }
}
