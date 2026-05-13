package com.example.umc10th.domain.term.dto;

import java.util.List;

import com.example.umc10th.domain.term.enums.TermType;
import lombok.Builder;

public class TermResDTO {

    @Builder
    public record TermListDTO(
            List<TermPreviewDTO> terms
    ) {
    }

    @Builder
    public record TermPreviewDTO(
            Long termId,
            TermType termType,
            String termName,
            Boolean required
    ) {
    }

    @Builder
    public record TermDetailDTO(
            Long termId,
            TermType termType,
            String termName,
            Boolean required,
            String termContent
    ) {
    }
}