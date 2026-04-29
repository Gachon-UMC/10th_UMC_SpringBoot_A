package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    // GET /api/missions
    @Builder
    public record MissionList(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            List<MissionItem> missions
    ) {
        @Builder
        public record MissionItem(
                Long missionId,
                String storeName,
                String description,
                Integer reward,
                Boolean isComplete
        ) {}
    }

    // POST /api/missions/{missionId}/challenge
    @Builder
    public record Challenge(
            Long missionId,
            String status
    ) {}

    // POST /api/missions/{missionId}/complete-request
    @Builder
    public record CompleteRequest(
            Long missionId,
            String status
    ) {}

    // PATCH /api/missions/{missionId}/verify
    @Builder
    public record Verify(
            Long missionId,
            String status,
            Integer rewardEarned
    ) {}
}