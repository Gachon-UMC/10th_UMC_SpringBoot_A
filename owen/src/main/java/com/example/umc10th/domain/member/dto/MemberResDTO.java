package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import java.util.List;

public class MemberResDTO {

    // GET /api/home
    @Builder
    public record Home(
            String name,
            Integer point,
            List<MissionPreview> ongoingMissions
    ) {
        @Builder
        public record MissionPreview(
                Long missionId,
                String storeName,
                String description,
                Integer reward
        ) {}
    }

    // GET /api/members  (마이페이지)
    @Builder
    public record MyPage(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    // PATCH /api/members
    @Builder
    public record UpdateInfo(
            Long memberId,
            String name,
            String phoneNumber,
            String address
    ) {}

    // DELETE /api/members
    @Builder
    public record Withdraw(
            Long memberId
    ) {}

    // GET /api/members/points
    @Builder
    public record Point(
            Long memberId,
            Integer point
    ) {}
}