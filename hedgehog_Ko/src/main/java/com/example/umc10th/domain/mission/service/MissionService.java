package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.mapping.UserMission;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MyMissionListDTO getMyMissions(
            Long userId,
            Long regionId,
            MissionStatus status,
            Long cursor,
            Integer size
    ) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        int pageSize = size == null ? 10 : size;
        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);

        List<UserMission> userMissions = userMissionRepository.findMyMissions(
                userId,
                regionId,
                status,
                cursor,
                pageRequest
        );

        boolean hasNext = userMissions.size() > pageSize;

        if (hasNext) {
            userMissions = userMissions.subList(0, pageSize);
        }

        Long nextCursor = userMissions.isEmpty()
                ? null
                : userMissions.get(userMissions.size() - 1).getId();

        return MissionConverter.toMyMissionListDTO(
                userMissions,
                nextCursor,
                hasNext,
                userMissions.size()
        );
    }
}