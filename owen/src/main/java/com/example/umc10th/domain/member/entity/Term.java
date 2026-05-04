package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "term")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private com.example.umc10th.domain.member.enums.Term name;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();
}