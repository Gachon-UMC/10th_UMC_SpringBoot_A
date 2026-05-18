package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Notification;
import com.example.umc10th.domain.member.entity.NotificationSetting;
import com.example.umc10th.domain.member.entity.PreferredFood;
import com.example.umc10th.domain.member.entity.ServiceAcceptance;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.NotificationRepository;
import com.example.umc10th.domain.member.repository.NotificationSettingRepository;
import com.example.umc10th.domain.member.repository.PreferredFoodRepository;
import com.example.umc10th.domain.member.repository.ServiceAcceptanceRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PreferredFoodRepository preferredFoodRepository;
    private final ServiceAcceptanceRepository serviceAcceptanceRepository;
    private final NotificationSettingRepository notificationSettingRepository;
    private final NotificationRepository notificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public MemberResponseDTO.CreateResultDTO createMember(MemberRequestDTO.CreateDTO request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_EMAIL_ALREADY_EXISTS);
        }

        User newUser = MemberConverter.toUser(request, passwordEncoder.encode(request.password()));
        User savedUser = memberRepository.save(newUser);

        if (request.preferred_food() != null) {
            request.preferred_food().forEach(foodId -> {
                foodRepository.findById(foodId).ifPresent(food -> {
                    PreferredFood preferredFood = PreferredFood.builder()
                        .user(savedUser)
                        .food(food)
                        .build();
                    preferredFoodRepository.save(preferredFood);
                });
            });
        }

        if (request.acceptance() != null) {
            request.acceptance().forEach(termId -> {
                termRepository.findById(termId).ifPresent(term -> {
                    ServiceAcceptance acceptance = ServiceAcceptance.builder()
                        .user(savedUser)
                        .term(term)
                        .build();
                    serviceAcceptanceRepository.save(acceptance);
                });
            });
        }

        return MemberConverter.toCreateResultDTO(savedUser);
    }

    public MemberResponseDTO.MemberInfoDTO getMember(Long userId) {
        User user = memberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(user);
    }

    public MemberResponseDTO.PointInfoDTO getMemberPoints(Long userId) {
        User user = memberRepository.findById(userId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toPointInfoDTO(user);
    }

    @Transactional
    public MemberResponseDTO.LoginResultDTO login(MemberRequestDTO.LoginDTO request, HttpServletRequest httpServletRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            AuthMember authMember = (AuthMember) authentication.getPrincipal();
            return MemberConverter.toLoginResultDTO(authMember.getUser());
        } catch (BadCredentialsException e) {
            throw new MemberException(MemberErrorCode.MEMBER_LOGIN_FAILED);
        } catch (AuthenticationException e) {
            throw new MemberException(MemberErrorCode.MEMBER_LOGIN_FAILED);
        }
    }
}
