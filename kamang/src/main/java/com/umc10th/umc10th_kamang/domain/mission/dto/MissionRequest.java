package com.umc10th.umc10th_kamang.domain.mission.dto;

import lombok.Getter;

public class MissionRequest {

    /**
     * 미션 상태 변경 요청 DTO
     * PATCH /api/user-missions/{userMissionId}
     */
    @Getter
    public static class UpdateStatusDTO {
        private String status;
    }
}
