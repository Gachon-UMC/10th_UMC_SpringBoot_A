package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 6주차 유지
    @Transactional
    public ReviewResDTO.Create createReview(Long storeId, Long memberId, ReviewReqDTO.Create req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Review review = ReviewConverter.toEntity(req, member, store);
        review = reviewRepository.save(review);
        return ReviewConverter.toCreateRes(review);
    }

    // [7주차] 내 리뷰 조회 - ID 순 (커서 기반)
    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewList getMyReviewsById(Long memberId, Long cursor, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // size+1개를 조회해서 다음 페이지 존재 여부 판단
        List<Review> reviews = reviewRepository.findMyReviewsByIdCursor(
                memberId, cursor, PageRequest.of(0, size + 1));

        return ReviewConverter.toMyReviewListById(reviews, size);
    }

    // [7주차] 내 리뷰 조회 - 별점 순 (커서 기반)
    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewList getMyReviewsByStar(Long memberId, Float starCursor, Long idCursor, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        List<Review> reviews = reviewRepository.findMyReviewsByStarCursor(
                memberId, starCursor, idCursor, PageRequest.of(0, size + 1));

        return ReviewConverter.toMyReviewListByStar(reviews, size);
    }
}