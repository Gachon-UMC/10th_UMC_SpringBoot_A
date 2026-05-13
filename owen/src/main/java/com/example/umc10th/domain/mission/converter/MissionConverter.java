package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MissionConverter {

    // 내 미션 목록 변환 (기존)
    public static MissionResDTO.MissionList toMissionList(Page<MemberMission> page) {
        return new MissionResDTO.MissionList(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(),
                page.getContent().stream().map(mm -> {
                    Mission m = mm.getMission();
                    return new MissionResDTO.MissionList.MissionItem(
                            m.getId(), m.getStore().getName(), m.getConditional(), m.getPoint(), mm.getIsComplete());
                }).toList()
        );
    }

    // [8번 반영] 홈 화면 미션 목록 변환 — 별도 DTO 사용
    public static MissionResDTO.HomeMissionList toHomeMissionList(Page<Mission> page) {
        return new MissionResDTO.HomeMissionList(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(),
                page.getContent().stream().map(m -> new MissionResDTO.HomeMissionList.HomeMissionItem(
                        m.getId(), m.getStore().getName(), m.getConditional(), m.getPoint(),
                        (int) ChronoUnit.DAYS.between(LocalDate.now(), m.getDeadline())
                )).toList()
        );
    }
}