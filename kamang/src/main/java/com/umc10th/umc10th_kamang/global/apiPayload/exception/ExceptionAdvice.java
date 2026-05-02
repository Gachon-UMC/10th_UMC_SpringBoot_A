package com.umc10th.umc10th_kamang.global.apiPayload.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 프로젝트에서 발생한 예외 처리
     * GeneralException을 잡으면 하위 도메인 Exception(UserException, MissionException 등)도 전부
     * 잡힘
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(GeneralException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        log.error("ProjectException: {} | code: {}", errorCode.getMessage(), errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    /**
     * 그 외의 정의되지 않은 모든 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Unhandled Exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
