package com.example.umc10th.domain.region.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name", nullable = false, length = 50, unique = true)
    private String regionName;

    // 생성자 메소드 레벨의 `@Builder`
    // 이유 1. setter를 막음으로 외부에서 값을 바꾸지 못하게 함.
    // 이유 2. 대신 객체를 만들어야 할 때, Entity명.builder().필드1명().필드2명(). ... .build(); 처럼 사용하도록 함.
    @Builder
    private Region(String regionName) {
        this.regionName = regionName;
    }

}
