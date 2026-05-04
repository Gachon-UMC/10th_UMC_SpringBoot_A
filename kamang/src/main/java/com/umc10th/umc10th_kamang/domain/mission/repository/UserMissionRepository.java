package com.umc10th.umc10th_kamang.domain.mission.repository;

import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
