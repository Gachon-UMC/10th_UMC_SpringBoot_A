package com.umc10th.umc10th_kamang.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        // 로그인하지 않은 사용자가 Private API에 접근하면 HTML 대신 통일된 JSON 응답 반환
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
