package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "member")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 5)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Address address = Address.안양동;

    @Column(name = "detail_address")
    private String detailAddress;

    // [8주차] 폼 로그인 사용자(socialType=LOCAL)는 socialId 가 빈 문자열일 수 있어 nullable=true 로 완화
    @Column(name = "social_id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(nullable = false, length = 50)
    private String email;

    // [8주차 추가] BCrypt 해시는 60자. 여유 두어 100자.
    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "profile_url", columnDefinition = "TEXT")
    private String profileUrl;
}