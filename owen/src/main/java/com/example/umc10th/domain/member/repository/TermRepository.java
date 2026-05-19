package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.enums.TermType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, Long> {

    // [8주차] TermType 으로 마스터 약관 조회
    Optional<Term> findByName(TermType name);
}