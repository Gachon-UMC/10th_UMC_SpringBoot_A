package com.umc10th.umc10th_kamang.domain.mission.repository;

import com.umc10th.umc10th_kamang.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
