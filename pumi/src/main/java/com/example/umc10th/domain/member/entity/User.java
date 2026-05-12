package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberRole;
import com.example.umc10th.domain.member.enums.MemberStatus;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable = true)
    private LocalDate birthday;

    @Column(nullable = true, length = 100)
    private String address;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private MemberRole role;

    @Column(nullable = true, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    public void updateName(String name) {
        this.name = name;
    }
}
