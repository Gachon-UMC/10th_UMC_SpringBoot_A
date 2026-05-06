package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="point", nullable = false)
    private Integer point;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;



}
