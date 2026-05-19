package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Review createReview(ReviewReqDTO.CreateReview request, Long memberId, Long storeId ){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RuntimeException("멤버 없음"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new RuntimeException("가게 없음"));

        Review review = Review.builder()
                .review(request.reviewContent())
                .star(request.star().floatValue())
                .member(member)
                .store(store)
                .build();

        return reviewRepository.save(review);

    }

    public ReviewResDTO.MyReviewResDTO getMyReviews(Long memberId, Long cursor, int size, String sort) {
        Long finalCursor = cursor != null ? cursor : 0L;
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Review> reviews = sort.equals("RATING")
                ? reviewRepository.findMyReviewsWithCursorOrderByStar(memberId, finalCursor, pageable)
                : reviewRepository.findMyReviewsWithCursorOrderById(memberId, finalCursor, pageable);

        boolean hasNext = reviews.size() > size;
        List<Review> resultReviews = reviews.stream().limit(size).toList();

        List<ReviewResDTO.ReviewInfo> reviewInfos = resultReviews.stream()
                .map(ReviewConverter::toReviewInfo)
                .toList();

        Long nextCursor = hasNext ? resultReviews.get(resultReviews.size() - 1).getId() : null;

        return ReviewResDTO.MyReviewResDTO.builder()
                .reviews(reviewInfos)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
