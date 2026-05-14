package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Address address;

    @Column(nullable = false, length = 40)
    private String detailAddress;

    @Column(nullable = false, length = 50)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    private SocialType socialType;

    @ColumnDefault("0")
    private Integer point;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String profileUrl;

    private LocalDateTime deletedAt;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFood> userFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerm> userTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}
