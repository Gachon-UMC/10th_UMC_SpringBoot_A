package com.example.umc10th.domain.member.entity;


import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="`member`")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="password", nullable = false)
    private String password;


    @Column(name="name", nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name="detail_address", nullable = false)
    private String detailAddress;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="total_point")
    private Integer totalPoint;


    @Column(name = "social_uid")
    private String socialUid;

    @Column(name="social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
