package com.umc10th.umc10th_kamang.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc10th.umc10th_kamang.global.apiPayload.ApiResponse;
import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        // 인증은 되었지만 권한이 부족한 경우도 기존 ApiResponse 실패 포맷으로 맞춤
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.FORBIDDEN;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
