package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            Long id,//리뷰 아이디
            String name, // 사용자 이름
            String review, //리뷰 내용
            Float star, //별
            LocalDateTime createdAt, //생성일자

            String responseContent,
            LocalDateTime responseCreatedAt,
            Long storeId
    ){}

    @Builder
    public  record MyReviewResDTO(
            List<ReviewInfo> reviews,
            Long nextCursor,
            Boolean hasNext
    ){}


}
