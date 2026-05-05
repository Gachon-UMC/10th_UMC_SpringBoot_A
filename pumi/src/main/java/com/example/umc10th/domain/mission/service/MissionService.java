package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.LocationRepository;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    public MissionResponseDTO.MissionListDTO getMemberMissions(Long userId, Boolean isCompleted, Long cursor) {
        User user = memberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Slice<MemberMission> memberMissionSlice = memberMissionRepository.findByUserIdWithCursor(
                user,
                isCompleted,
                cursor,
                PageRequest.of(0, 10)
        );

        return MissionConverter.toMemberMissionListDTO(memberMissionSlice);
    }

    public MissionResponseDTO.MissionStatsDTO getMissionCountByRegion(Long userId, Long regionId) {
        User user = memberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Location location = locationRepository.findById(regionId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.REGION_NOT_FOUND));

        Long count = memberMissionRepository.countSuccessfulMissionsByUserIdAndRegionId(user, location);

        return new MissionResponseDTO.MissionStatsDTO(location.getName(), count);
    }

    @Transactional
    public MissionResponseDTO.MissionChallengeResultDTO createMissionChallenge(Long userId, Long missionId) {
        User user = memberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
            .user(user)
            .mission(mission)
            .isCompleted(false)
            .build();

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);
        return MissionConverter.toMissionChallengeResultDTO(savedMemberMission);
    }

    @Transactional
    public void updateMissionCompletion(Long missionId) {
        MemberMission memberMission = memberMissionRepository.findById(missionId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));
        
        memberMission.complete();
    }
}
