package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResponseDTO.ReviewResultDTO createReview(Long userId, Long userMissionId, ReviewRequestDTO.WriteReviewDTO request) {
        UserMission userMission = userMissionRepository.findByIdAndUserId(userMissionId, userId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));
        User user = userMission.getUser();

        Review review = ReviewConverter.toReview(
            request, 
            user,
            userMission.getMission().getStore(),
            userMission
        );
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewResultDTO(savedReview);
    }

    public ReviewResponseDTO.MyReviewListDTO getMyReviews(Long userId, ReviewRequestDTO.MyReviewsRequestDTO request) {
        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Slice<Review> reviewSlice;
        if (request.sort() == ReviewRequestDTO.ReviewSortType.RATING) {
            Float cursorRate = null;
            if (request.cursor() != null) {
                Review cursorReview = reviewRepository.findByIdAndUser(request.cursor(), user)
                        .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));
                cursorRate = cursorReview.getRate();
            }
            reviewSlice = reviewRepository.findMyReviewsByRatingSort(
                    user,
                    request.cursor(),
                    cursorRate,
                    PageRequest.of(0, request.size())
            );
        } else {
            reviewSlice = reviewRepository.findMyReviewsByIdSort(
                    user,
                    request.cursor(),
                    PageRequest.of(0, request.size())
            );
        }

        return ReviewConverter.toMyReviewListDTO(reviewSlice);
    }
}
