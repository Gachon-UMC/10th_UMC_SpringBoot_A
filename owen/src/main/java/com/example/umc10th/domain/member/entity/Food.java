package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "food")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}