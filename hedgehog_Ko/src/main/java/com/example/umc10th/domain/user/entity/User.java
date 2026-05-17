package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_user_social", columnNames = {"social_uid", "social_type"})
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 20)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "detail_address", nullable = false, length = 255)
    private String detailAddress;

    @Column(name = "social_uid", length = 255)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 30)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    /* 생성자 레벨의 `@Builder` 유지
    클래스 레벨의 `@Builder`의 경우 필요한 인자만 설정할 수 없어 위험할 수 있기 때문.
    그래서, 일반적으로 클래스 레벨에 `@Builder` 를 적용하는 것이 더 일반적이고 간편하지만,
    복잡한 도메인 모델이나 염격한 객체 생성 규칙을 가진 경우에는 생성자 레벨에서의 적용이 더 적합

    저는 단순 DTO나 데이터 모델의 경우에는 클래스 레벨의 `@Builder`를 사용하겠지만,
    Entity의 경우에는 생성자 레벨의 `@Builder`를 유지하겠음.

    출처:
    https://velog.io/@ichubtou/Lombok-Builder-Class-vs-Constructor-Level
    https://velog.io/@park2348190/Lombok-Builder%EC%9D%98-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC

     - 이유:
     1. id 속성이 `@GeneratedValue(strategy = GenerationType.IDENTITY)`이므로 외부에서 임의로 id를 넣는 경우 문제가 발생함.
     2. 외부에서 point를 조작할 수 있음. 예를 들면,
     ```Java
     User.builder()
        .id(1L)
        .name("고슴이")
        .point(999999)
        .build();
     ```
     와 같이 공격할 수 있다고 함.

     아래처럼 코드가 길지만... 생성자 레벨로 두겠습니다.
    */
    @Builder
    private User(
            String name,
            String nickname,
            Gender gender,
            LocalDate birth,
            String address,
            String detailAddress,
            String socialUid,
            SocialType socialType,
            String email,
            String password,
            String phoneNumber,
            String profileImageUrl
    ) {
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.detailAddress = detailAddress;
        this.socialUid = socialUid;
        this.socialType = socialType;
        this.point = 0;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateMyInfo(
            String nickname,
            String address,
            String detailAddress,
            String profileImageUrl
    ) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (address != null) {
            this.address = address;
        }
        if (detailAddress != null) {
            this.detailAddress = detailAddress;
        }
        if (profileImageUrl != null) {
            this.profileImageUrl = profileImageUrl;
        }
    }

    public void addPoint(Integer point) {
        this.point += point;
    }
}
