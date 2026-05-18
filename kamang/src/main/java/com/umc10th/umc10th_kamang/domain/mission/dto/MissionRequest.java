package com.umc10th.umc10th_kamang.domain.mission.dto;

import lombok.Getter;

public class MissionRequest {

    /**
     * 내 미션 목록 조회 요청 DTO
     */
    @Getter
    public static class MyMissionListDTO {
        private Long userId;
        private String status;
        private Integer page = 0;
        private Integer size = 10;
    }

    /**
     * 미션 상태 변경 요청 DTO
     * PATCH /api/user-missions/{userMissionId}
     */
    @Getter
    public static class UpdateStatusDTO {
        private String status;
    }
}
