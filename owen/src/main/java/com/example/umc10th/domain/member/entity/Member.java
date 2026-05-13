package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "profile_url", columnDefinition = "TEXT")
    private String profileUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}