package com.umc10th.umc10th_kamang.domain.mission.exception;

import com.umc10th.umc10th_kamang.global.apiPayload.code.BaseErrorCode;
import com.umc10th.umc10th_kamang.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
