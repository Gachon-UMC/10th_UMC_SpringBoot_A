package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(15)")
    private FoodCategory name;

    @Builder.Default
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<UserFood> userFoodList = new ArrayList<>();
}
