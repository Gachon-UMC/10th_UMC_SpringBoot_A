package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * AccessDeniedHandler / AuthenticationEntryPoint 가 공통으로 사용하는
 * ApiResponse JSON 응답 쓰기 유틸. (필터 단 예외는 @RestControllerAdvice 가 잡지 못하므로 직접 응답)
 */
public final class SecurityResponseWriter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private SecurityResponseWriter() {}

    public static void write(HttpServletResponse response, BaseErrorCode code) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> body = ApiResponse.onFailure(code, null);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), body);
    }
}