package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.LocationRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    public MissionResponseDTO.OngoingMissionListDTO getOngoingMissions(Long userId, Integer page, Integer size) {
        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<UserMission> ongoingMissions = userMissionRepository.findOngoingMissionsByUser(
                user,
                PageRequest.of(page, size)
        );

        return MissionConverter.toOngoingMissionListDTO(ongoingMissions);
    }

    public MissionResponseDTO.MissionListDTO getUserMissions(Long userId, Boolean isCompleted, Long regionId, Long cursor) {
        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Location location = (regionId != null)
                ? locationRepository.findById(regionId)
                        .orElseThrow(() -> new MissionException(MissionErrorCode.REGION_NOT_FOUND))
                : null;

        Slice<UserMission> userMissionSlice = userMissionRepository.findByUserWithCursor(
                user,
                isCompleted,
                location,
                cursor,
                PageRequest.of(0, 10)
        );

        return MissionConverter.toUserMissionListDTO(userMissionSlice);
    }

    public MissionResponseDTO.MissionStatsDTO getMissionCountByRegion(Long userId, Long regionId) {
        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Location location = locationRepository.findById(regionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.REGION_NOT_FOUND));

        Long count = userMissionRepository.countSuccessfulMissionsByUserAndRegion(user, location);
        return new MissionResponseDTO.MissionStatsDTO(location.getName(), count);
    }

    @Transactional
    public MissionResponseDTO.MissionChallengeResultDTO createMissionChallenge(Long userId, Long missionId) {
        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .isCompleted(false)
                .build();

        UserMission savedUserMission = userMissionRepository.save(userMission);
        return MissionConverter.toMissionChallengeResultDTO(savedUserMission);
    }

    @Transactional
    public void updateMissionCompletion(Long userId, Long userMissionId) {
        UserMission userMission = userMissionRepository.findByIdAndUserId(userMissionId, userId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));

        userMission.complete();
    }
}
