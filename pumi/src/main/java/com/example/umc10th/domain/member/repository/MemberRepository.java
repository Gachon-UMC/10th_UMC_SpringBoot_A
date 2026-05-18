package com.example.umc10th.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc10th.domain.member.entity.User;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
