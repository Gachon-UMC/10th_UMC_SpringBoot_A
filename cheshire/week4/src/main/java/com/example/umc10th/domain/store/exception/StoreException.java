package com.example.umc10th.domain.store.exception;

import com.example.umc10th.domain.store.exception.code.StoreErrorCode;

public class StoreException extends RuntimeException {
    private StoreErrorCode errorCode;  // 여기에 저장해두기

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode.getMessage());  // 메시지만 RuntimeException에 넘김
        this.errorCode = errorCode;      // 전체 정보는 따로 저장
    }

    public StoreErrorCode getErrorCode() {  // 나중에 꺼내쓸 수 있게
        return errorCode;
    }
}
