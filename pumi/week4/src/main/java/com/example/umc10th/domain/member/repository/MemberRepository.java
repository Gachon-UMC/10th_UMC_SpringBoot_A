package com.example.umc10th.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc10th.domain.member.entity.User;

public interface MemberRepository extends JpaRepository<User, Long> {
}
