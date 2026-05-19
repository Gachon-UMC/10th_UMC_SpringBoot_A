package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // [8주차] 폼 로그인 — UserDetailsService.loadUserByUsername 에서 사용
    Optional<Member> findByEmail(String email);

    // [8주차] 회원가입 중복 검사
    boolean existsByEmail(String email);
}