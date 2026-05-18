package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return new MissionResponseDTO.MissionPreviewDTO(
            mission.getId(),
            mission.getStore().getName(),
            mission.getConditionPrice(),
            mission.getAwardPoint(),
            mission.getDeadline(),
            false
        );
    }

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();
        return new MissionResponseDTO.MissionPreviewDTO(
            mission.getId(),
            mission.getStore().getName(),
            mission.getConditionPrice(),
            mission.getAwardPoint(),
            mission.getDeadline(),
            userMission.getIsCompleted()
        );
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(List<Mission> missions) {
        List<MissionResponseDTO.MissionPreviewDTO> previewDTOs = missions.stream()
            .map(MissionConverter::toMissionPreviewDTO)
            .collect(Collectors.toList());
        return new MissionResponseDTO.MissionListDTO(
            previewDTOs,
            null
        );
    }

    public static MissionResponseDTO.MissionListDTO toUserMissionListDTO(Slice<UserMission> userMissionSlice) {
        List<UserMission> content = userMissionSlice.getContent();

        List<MissionResponseDTO.MissionPreviewDTO> previewDTOs = content.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return new MissionResponseDTO.MissionListDTO(previewDTOs, nextCursor);
    }

    public static MissionResponseDTO.OngoingMissionListDTO toOngoingMissionListDTO(Page<UserMission> ongoingMissions) {
        List<MissionResponseDTO.MissionPreviewDTO> previewDTOs = ongoingMissions.getContent().stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return new MissionResponseDTO.OngoingMissionListDTO(
                previewDTOs,
                previewDTOs.size(),
                ongoingMissions.getTotalPages(),
                ongoingMissions.getTotalElements(),
                ongoingMissions.isFirst(),
                ongoingMissions.isLast()
        );
    }

    public static MissionResponseDTO.MissionChallengeResultDTO toMissionChallengeResultDTO(UserMission userMission) {
        return new MissionResponseDTO.MissionChallengeResultDTO(
            userMission.getId(),
            userMission.getCreatedAt()
        );
    }
}
