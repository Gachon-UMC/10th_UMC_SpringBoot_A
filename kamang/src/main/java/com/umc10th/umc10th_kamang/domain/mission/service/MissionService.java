package com.umc10th.umc10th_kamang.domain.mission.service;

import com.umc10th.umc10th_kamang.domain.category.entity.Region;
import com.umc10th.umc10th_kamang.domain.category.exception.CategoryErrorCode;
import com.umc10th.umc10th_kamang.domain.category.exception.CategoryException;
import com.umc10th.umc10th_kamang.domain.category.repository.RegionRepository;
import com.umc10th.umc10th_kamang.domain.mission.converter.MissionConverter;
import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.domain.mission.entity.Mission;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionErrorCode;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionException;
import com.umc10th.umc10th_kamang.domain.mission.repository.MissionRepository;
import com.umc10th.umc10th_kamang.domain.mission.repository.UserMissionRepository;
import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.domain.user.exception.UserErrorCode;
import com.umc10th.umc10th_kamang.domain.user.exception.UserException;
import com.umc10th.umc10th_kamang.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final int MISSION_GOAL_COUNT = 10;
    private static final int GOAL_REWARD_POINTS = 1000;

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    public MissionResponse.HomeDTO getHome(
            Long userId,
            Long regionId,
            Integer page,
            Integer size
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Region region = resolveRegion(user, regionId);

        Long completedMissionCount = userMissionRepository.countCompletedMissionsByUser(
                userId,
                MissionStatus.COMPLETED
        );

        Page<Mission> missions = missionRepository.findChallengeableMissionsByRegion(
                userId,
                region.getId(),
                LocalDateTime.now(),
                PageRequest.of(page, size)
        );

        return MissionConverter.toHomeDTO(
                missions,
                region,
                user.getTotalPoints(),
                completedMissionCount,
                MISSION_GOAL_COUNT,
                GOAL_REWARD_POINTS
        );
    }

    public MissionResponse.MissionListDTO getUserMissions(
            Long userId,
            String status,
            Integer page,
            Integer size
    ) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MissionStatus missionStatus = parseMissionStatus(status);
        validateListStatus(missionStatus);

        Page<UserMission> userMissions = userMissionRepository.findUserMissionsByUserAndStatus(
                userId,
                missionStatus,
                PageRequest.of(page, size)
        );

        return MissionConverter.toMissionListDTO(userMissions);
    }

    private MissionStatus parseMissionStatus(String status) {
        try {
            return MissionStatus.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new MissionException(MissionErrorCode.MISSION_STATUS_INVALID);
        }
    }

    private void validateListStatus(MissionStatus status) {
        if (status != MissionStatus.PROCEEDING && status != MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.MISSION_STATUS_INVALID);
        }
    }

    private Region resolveRegion(User user, Long regionId) {
        if (regionId != null) {
            return regionRepository.findById(regionId)
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.REGION_NOT_FOUND));
        }

        Region region = user.getRegion();
        if (region == null) {
            throw new CategoryException(CategoryErrorCode.REGION_NOT_FOUND);
        }

        return region;
    }
}
