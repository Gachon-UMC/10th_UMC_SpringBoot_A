package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    public record PointInfoDTO(
        Integer totalPoint
    ) {}

    public record MissionPreviewDTO(
        Long missionId,
        String storeName,
        Long conditionPrice,
        Long awardPoint,
        LocalDateTime deadline,
        Boolean isCompleted
    ) {}

    public record MissionListDTO(
        List<MissionPreviewDTO> missions,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ) {}

    public record MissionStatsDTO(
        String regionName,
        Long successCount
    ) {}

    public record MissionStatsListDTO(
        List<MissionStatsDTO> stats
    ) {}

    public record MissionChallengeResultDTO(
        Long memberMissionId,
        LocalDateTime createdAt
    ) {}
}
