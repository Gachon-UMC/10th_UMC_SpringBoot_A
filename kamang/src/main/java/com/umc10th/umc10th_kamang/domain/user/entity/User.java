package com.umc10th.umc10th_kamang.domain.user.entity;

import com.umc10th.umc10th_kamang.domain.category.entity.Region;
import com.umc10th.umc10th_kamang.domain.user.enums.Gender;
import com.umc10th.umc10th_kamang.domain.user.enums.SocialType;
import com.umc10th.umc10th_kamang.domain.user.enums.UserStatus;
import com.umc10th.umc10th_kamang.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, length = 20)
    private SocialType socialType;

    @Column(name = "social_id", nullable = false, unique = true, length = 255)
    private String socialId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Builder.Default
    @ColumnDefault("false")
    @Column(name = "is_phone_verified", nullable = false)
    private Boolean isPhoneVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "total_points", nullable = false)
    private Integer totalPoints = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder.Default
    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 15)
    private UserStatus status = UserStatus.ACTIVE;
}
