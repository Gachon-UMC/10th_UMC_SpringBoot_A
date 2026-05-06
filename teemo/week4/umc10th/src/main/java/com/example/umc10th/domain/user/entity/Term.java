package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean optional;

    @Builder.Default
    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<UserTerm> userTermList = new ArrayList<>();
}
