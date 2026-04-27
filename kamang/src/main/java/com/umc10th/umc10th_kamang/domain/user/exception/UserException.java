package com.umc10th.umc10th_kamang.domain.user.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException {

    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
