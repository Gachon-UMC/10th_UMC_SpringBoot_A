package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
