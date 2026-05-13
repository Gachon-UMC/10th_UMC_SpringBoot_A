package com.example.umc10th.global.auth;

import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    // JWT를 아직 배우지 않아 임시로 고정한 사용자 ID
    private static final Long TEMP_USER_ID = 1L;

    public Long getCurrentUserId() {
        return TEMP_USER_ID;
    }
}
