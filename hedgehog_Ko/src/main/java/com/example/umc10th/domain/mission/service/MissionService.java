package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserMission;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final String TEMP_VERIFICATION_CODE = "482913";
    private static final int VERIFICATION_EXPIRE_MINUTES = 10;

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

    @Transactional
    public MissionResDTO.StartMissionResultDTO startMission(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateStartable(userMission);

        userMission.start();
        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);

        return MissionConverter.toStartMissionResultDTO(savedUserMission);
    }

    @Transactional
    public MissionResDTO.CancelMissionResultDTO cancelMission(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateCancelable(userMission);

        userMission.cancel();
        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);

        return MissionConverter.toCancelMissionResultDTO(savedUserMission);
    }

    public MissionResDTO.VerificationRequestResultDTO requestMissionVerification(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateVerifiable(userMission);

        return MissionConverter.toVerificationRequestResultDTO(
                userMission,
                TEMP_VERIFICATION_CODE,
                LocalDateTime.now().plusMinutes(VERIFICATION_EXPIRE_MINUTES)
        );
    }

    @Transactional
    public MissionResDTO.VerificationConfirmResultDTO confirmMissionVerification(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateVerifiable(userMission);

        Integer earnedPoint = userMission.getMission().getPoint();
        User user = userMission.getUser();

        userMission.complete();
        user.addPoint(earnedPoint);

        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);
        userRepository.saveAndFlush(user);

        return MissionConverter.toVerificationConfirmResultDTO(savedUserMission, earnedPoint);
    }

    private UserMission getMyUserMission(Long userId, Long userMissionId) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        UserMission userMission = userMissionRepository.findActiveUserMissionById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));

        if (!userMission.getUser().getId().equals(userId)) {
            throw new MissionException(MissionErrorCode.FORBIDDEN_USER_MISSION);
        }

        return userMission;
    }

    private void validateStartable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.ASSIGNED) {
            return;
        }
        if (status == MissionStatus.CHALLENGING) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING_MISSION);
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

    private void validateCancelable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.ASSIGNED || status == MissionStatus.CHALLENGING) {
            return;
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

    private void validateVerifiable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.CHALLENGING) {
            return;
        }
        if (status == MissionStatus.ASSIGNED) {
            throw new MissionException(MissionErrorCode.MISSION_VERIFICATION_NOT_REQUESTED);
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }
}
