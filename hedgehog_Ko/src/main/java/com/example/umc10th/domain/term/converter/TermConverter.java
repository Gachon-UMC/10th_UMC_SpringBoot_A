package com.example.umc10th.domain.term.converter;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.Term;

import java.util.List;

public class TermConverter {

    public static TermResDTO.TermPreviewDTO toTermPreviewDTO(Term term) {
        return TermResDTO.TermPreviewDTO.builder()
                .termId(term.getId())
                .termType(term.getType())
                .termName(term.getName())
                .required(term.getRequired())
                .build();
    }

    public static TermResDTO.TermListDTO toTermListDTO(List<Term> terms) {
        return TermResDTO.TermListDTO.builder()
                .terms(terms.stream()
                        .map(TermConverter::toTermPreviewDTO)
                        .toList())
                .build();
    }

    public static TermResDTO.TermDetailDTO toTermDetailDTO(Term term) {
        return TermResDTO.TermDetailDTO.builder()
                .termId(term.getId())
                .termType(term.getType())
                .termName(term.getName())
                .required(term.getRequired())
                .termContent(term.getContent())
                .build();
    }
}
