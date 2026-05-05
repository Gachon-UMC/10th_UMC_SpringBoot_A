package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user_preference_food",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_preference_food_user_food",
                        columnNames = {"user_id", "food_category_id"}
                )
        }
)
public class UserPreferenceFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;

    @Builder
    private UserPreferenceFood(
            User user,
            FoodCategory foodCategory
    ) {
        this.user = user;
        this.foodCategory = foodCategory;
    }
}
