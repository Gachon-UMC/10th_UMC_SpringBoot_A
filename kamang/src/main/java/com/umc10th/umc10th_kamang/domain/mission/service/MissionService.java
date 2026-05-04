package com.umc10th.umc10th_kamang.domain.mission.service;

import com.umc10th.umc10th_kamang.domain.mission.converter.MissionConverter;
import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionErrorCode;
import com.umc10th.umc10th_kamang.domain.mission.exception.MissionException;
import com.umc10th.umc10th_kamang.domain.mission.repository.UserMissionRepository;
import com.umc10th.umc10th_kamang.domain.user.exception.UserErrorCode;
import com.umc10th.umc10th_kamang.domain.user.exception.UserException;
import com.umc10th.umc10th_kamang.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

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
}
