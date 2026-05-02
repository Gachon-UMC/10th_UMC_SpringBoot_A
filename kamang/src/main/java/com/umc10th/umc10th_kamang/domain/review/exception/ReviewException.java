package com.umc10th.umc10th_kamang.domain.review.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {

    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
