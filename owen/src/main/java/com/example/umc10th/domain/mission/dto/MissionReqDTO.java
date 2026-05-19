package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class MissionReqDTO {

    public record MyMissionReq(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            Boolean isComplete,

            @PositiveOrZero(message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page,

            @Positive(message = "페이지 크기는 1 이상이어야 합니다.")
            Integer size
    ) {
        public MyMissionReq {
            if (isComplete == null) isComplete = false;
            if (page == null) page = 0;
            if (size == null) size = 10;
        }
    }
}