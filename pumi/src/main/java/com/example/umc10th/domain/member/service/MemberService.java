package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public MemberResponseDTO.JoinResultDTO join(MemberRequestDTO.JoinDTO request) {
        return null;
    }

    public MemberResponseDTO.MemberInfoDTO getMe() {
        return null;
    }

    public MemberResponseDTO.MemberInfoDTO updateMe(MemberRequestDTO.UpdateDTO request) {
        return null;
    }

    public void deleteMe() {
    }

    public void updateNotificationSettings(MemberRequestDTO.UpdateNotificationSettingsDTO request) {
    }

    public MemberResponseDTO.NotificationListDTO getNotifications() {
        return null;
    }
}
