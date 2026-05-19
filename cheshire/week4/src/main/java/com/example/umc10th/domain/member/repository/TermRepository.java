package com.example.umc10th.domain.member.repository;


import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
    Optional<Term> findById(Long termId);
}
