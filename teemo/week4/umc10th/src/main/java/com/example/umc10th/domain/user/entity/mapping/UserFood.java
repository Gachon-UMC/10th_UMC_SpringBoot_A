package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public void setMapping(User user) {
        if (this.user != null) {
            this.user.getUserFoodList().remove(this);
        }
        this.user = user;
        user.getUserFoodList().add(this);
    }
}
