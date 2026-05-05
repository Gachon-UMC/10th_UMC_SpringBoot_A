package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.NotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
}
