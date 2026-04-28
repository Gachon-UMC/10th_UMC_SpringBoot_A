package com.example.umc10th.domain.category.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.exception.GeneralException;

public class CategoryException extends GeneralException {
    public CategoryException(BaseErrorCode code) {
        super(code);
    }
}
