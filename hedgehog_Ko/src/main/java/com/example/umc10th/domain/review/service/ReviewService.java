package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public ReviewResDTO.StoreReviewListDTO getStoreReviews(
            Long storeId,
            Long cursor,
            Integer size
    ) {
        validateCursor(cursor);
        storeRepository.findByIdAndDeletedAtIsNull(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        int pageSize = getPageSize(size);
        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);

        List<Review> reviews = reviewRepository.findStoreReviews(storeId, cursor, pageRequest);
        boolean hasNext = reviews.size() > pageSize;

        if (hasNext) {
            reviews = reviews.subList(0, pageSize);
        }

        return ReviewConverter.toStoreReviewListDTO(
                reviews,
                getNextCursor(reviews),
                hasNext,
                reviews.size()
        );
    }

    @Transactional
    public ReviewResDTO.CreateReviewResultDTO createReview(
            Long userId,
            Long storeId,
            ReviewReqDTO.CreateReviewDTO request
    ) {
        Store store = storeRepository.findByIdAndDeletedAtIsNull(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        User user = userRepository.findByIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, user);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }

    public ReviewResDTO.MyReviewListDTO getMyReviews(
            Long userId,
            Long cursor,
            Integer size
    ) {
        validateCursor(cursor);

        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        int pageSize = getPageSize(size);
        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);

        List<Review> reviews = reviewRepository.findMyReviews(userId, cursor, pageRequest);
        boolean hasNext = reviews.size() > pageSize;

        if (hasNext) {
            reviews = reviews.subList(0, pageSize);
        }

        return ReviewConverter.toMyReviewListDTO(
                reviews,
                getNextCursor(reviews),
                hasNext,
                reviews.size()
        );
    }

    @Transactional
    public ReviewResDTO.UpdateReviewResultDTO updateMyReview(
            Long userId,
            Long reviewId,
            ReviewReqDTO.UpdateReviewDTO request
    ) {
        Review review = getMyActiveReview(userId, reviewId);
        review.updateReview(request.content(), request.starRate());

        Review savedReview = reviewRepository.saveAndFlush(review);

        return ReviewConverter.toUpdateReviewResultDTO(savedReview);
    }

    @Transactional
    public ReviewResDTO.DeleteReviewResultDTO deleteMyReview(
            Long userId,
            Long reviewId
    ) {
        Review review = getMyActiveReview(userId, reviewId);
        review.delete();

        Review savedReview = reviewRepository.saveAndFlush(review);

        return ReviewConverter.toDeleteReviewResultDTO(savedReview);
    }

    private Review getMyActiveReview(Long userId, Long reviewId) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        Review review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!review.getUser().getId().equals(userId)) {
            throw new ReviewException(ReviewErrorCode.FORBIDDEN_REVIEW);
        }

        return review;
    }

    private int getPageSize(Integer size) {
        int pageSize = size == null ? 10 : size;

        if (pageSize <= 0) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_REQUEST);
        }

        return pageSize;
    }

    private void validateCursor(Long cursor) {
        if (cursor != null && cursor <= 0) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_CURSOR);
        }
    }

    private Long getNextCursor(List<Review> reviews) {
        return reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).getId();
    }
}
