package com.ll.ticket.domain.review.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.domain.review.dto.ReviewRequest;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.entity.Review;
import com.ll.ticket.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    public Long reviewCreate(ReviewRequest reviewWriteRequest , Concert concert) {

        //  현재 로그인한 사용자의 정보를 가져온다. , 작성자를 포함 하기 위함
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 현재 로그인한 사용자의 이메일
        String authorEmail = authentication.getName();

        Member member = memberService.findByEmail(authorEmail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        reviewWriteRequest.setMember(member);
        reviewWriteRequest.setConcert(concert);

        Review review =  reviewRepository.save(reviewWriteRequest.toEntity());

        return new ReviewResponse(review).getConcertId();

    }

    public List<ReviewResponse> getReviewsByConcertId(Long concertId) {
        return reviewRepository.findByConcertConcertId(concertId).stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    public Review findById (Long id) {

        return reviewRepository.findById(id).orElseThrow(()

                -> new RuntimeException("리뷰를 찾을 수 없습니다. "));
    }

    public ReviewResponse findReviewResponse(Long id) {

        Review review = findById(id);

        return new ReviewResponse(review);
    }
    @Transactional
    public ReviewResponse reviewUpdate(ReviewRequest reviewRequest , Long id) {

        Review review = findById(id);

        review.updateReviewContent(reviewRequest.getContent());

        Review saveReview = reviewRepository.save(review);

       return new ReviewResponse(saveReview);
    }

    @Transactional
    public void deleteById(Long id) {

     reviewRepository.deleteById(id);
    }
}
