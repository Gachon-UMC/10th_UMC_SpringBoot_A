package com.umc10th.umc10th_kamang.domain.category.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.exception.GeneralException;

public class CategoryException extends GeneralException {

    public CategoryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
