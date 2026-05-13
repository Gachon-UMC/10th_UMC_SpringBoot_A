package com.example.umc10th.domain.term.service;

import com.example.umc10th.domain.term.converter.TermConverter;
import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.domain.term.exception.TermException;
import com.example.umc10th.domain.term.exception.code.TermErrorCode;
import com.example.umc10th.domain.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermService {

    private final TermRepository termRepository;

    public TermResDTO.TermListDTO getTerms() {
        List<Term> terms = termRepository.findAll();
        return TermConverter.toTermListDTO(terms);
    }

    public TermResDTO.TermDetailDTO getTermDetail(Long termId) {
        Term term = termRepository.findById(termId)
                .orElseThrow(() -> new TermException(TermErrorCode.TERM_NOT_FOUND));

        return TermConverter.toTermDetailDTO(term);
    }
}
