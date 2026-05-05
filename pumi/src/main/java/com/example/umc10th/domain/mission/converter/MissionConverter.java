package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
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

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return new MissionResponseDTO.MissionPreviewDTO(
            mission.getId(),
            mission.getStore().getName(),
            mission.getConditionPrice(),
            mission.getAwardPoint(),
            mission.getDeadline(),
            memberMission.getIsCompleted()
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

    public static MissionResponseDTO.MissionListDTO toMemberMissionListDTO(Slice<MemberMission> memberMissionSlice) {
        List<MemberMission> content = memberMissionSlice.getContent();

        List<MissionResponseDTO.MissionPreviewDTO> previewDTOs = content.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return new MissionResponseDTO.MissionListDTO(previewDTOs, nextCursor);
    }

    public static MissionResponseDTO.MissionChallengeResultDTO toMissionChallengeResultDTO(MemberMission memberMission) {
        return new MissionResponseDTO.MissionChallengeResultDTO(
            memberMission.getId(),
            memberMission.getCreatedAt()
        );
    }
}
