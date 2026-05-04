package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResDTO.MissionList.MissionItem toMissionItem(MemberMission mm) {
        Mission m = mm.getMission();
        return new MissionResDTO.MissionList.MissionItem(
                m.getId(), m.getStore().getName(), m.getConditional(), m.getPoint(), mm.getIsComplete()
        );
    }

    public static MissionResDTO.MissionList toMissionList(Page<MemberMission> page) {
        return new MissionResDTO.MissionList(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(),
                page.getContent().stream().map(MissionConverter::toMissionItem).toList()
        );
    }

    public static MissionResDTO.MissionList.MissionItem toHomeMissionItem(Mission m) {
        return new MissionResDTO.MissionList.MissionItem(
                m.getId(), m.getStore().getName(), m.getConditional(), m.getPoint(), false
        );
    }

    public static MissionResDTO.MissionList toHomeMissionList(Page<Mission> page) {
        return new MissionResDTO.MissionList(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(),
                page.getContent().stream().map(MissionConverter::toHomeMissionItem).toList()
        );
    }
}