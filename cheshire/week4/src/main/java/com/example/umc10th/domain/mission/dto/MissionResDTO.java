package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member.enums.FoodName;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MissionResDTO {

    @Builder
    public record HomeDTO(
            String locationName,
            Integer totalPoint,
            Integer completedMissionCount,
            List<MissionInfo> missions
    ) {}
    public record MissionInfo(
                String storeName,
                FoodName storeCategory,
                String content,
                LocalDate dueDate,
                Integer point,
                MissionStatus status
    ) {}

    public enum MissionStatus {
        IN_PROGRESS,
        SUCCESS,
        NOT_STARTED
    }

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String content
    ){}

//   페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}
