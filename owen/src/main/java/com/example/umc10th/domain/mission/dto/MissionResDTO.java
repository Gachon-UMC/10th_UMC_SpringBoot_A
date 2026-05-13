package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    // 내 미션 목록 응답
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
                String conditional,
                Integer point,
                Boolean isComplete
        ) {}
    }

    // [8번 반영] 홈 화면 미션 목록 응답 — 별도 DTO
    @Builder
    public record HomeMissionList(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            List<HomeMissionItem> missions
    ) {
        @Builder
        public record HomeMissionItem(
                Long missionId,
                String storeName,
                String conditional,
                Integer point,
                Integer deadline
        ) {}
    }

    // 기존 유지
    public record Challenge(Long memberMissionId) {}
    public record CompleteRequest(Long memberMissionId) {}
    public record Verify(Long memberMissionId) {}
}