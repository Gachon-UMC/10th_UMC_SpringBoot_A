package com.example.umc10th.domain.mission.enums;

public enum MissionStatus {
    ASSIGNED,
    CHALLENGING,
    COMPLETED,
    EXPIRED,
    CANCELED // 사용자가 미션을 취소(포기)한 상태 -> 재도전 여부 등을 알 수 있음.
}
