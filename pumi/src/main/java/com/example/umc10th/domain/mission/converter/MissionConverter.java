package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

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
            previewDTOs.size(),
            1,
            (long) previewDTOs.size(),
            true,
            true
        );
    }

    public static MissionResponseDTO.MissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> previewDTOs = memberMissionPage.getContent().stream()
            .map(MissionConverter::toMissionPreviewDTO)
            .collect(Collectors.toList());
        
        return new MissionResponseDTO.MissionListDTO(
            previewDTOs,
            previewDTOs.size(),
            memberMissionPage.getTotalPages(),
            memberMissionPage.getTotalElements(),
            memberMissionPage.isFirst(),
            memberMissionPage.isLast()
        );
    }

    public static MissionResponseDTO.MissionChallengeResultDTO toMissionChallengeResultDTO(MemberMission memberMission) {
        return new MissionResponseDTO.MissionChallengeResultDTO(
            memberMission.getId(),
            memberMission.getCreatedAt()
        );
    }
}
