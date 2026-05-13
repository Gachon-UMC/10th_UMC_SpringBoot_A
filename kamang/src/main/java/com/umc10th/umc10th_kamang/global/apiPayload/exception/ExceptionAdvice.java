package com.umc10th.umc10th_kamang.global.apiPayload.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 프로젝트에서 직접 정의한 예외 처리
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(GeneralException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        log.error("ProjectException: {} | code: {}", errorCode.getMessage(), errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    /**
     * @Valid Request Body 검증 실패 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException e
    ) {
        BaseErrorCode errorCode = GeneralErrorCode.VALIDATION_FAILED;
        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.error("ValidationException: {}", errors);

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, errors));
    }

    /**
     * 그 외 정의하지 않은 모든 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Unhandled Exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
