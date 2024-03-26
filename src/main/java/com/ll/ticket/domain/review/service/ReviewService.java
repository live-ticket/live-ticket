package com.ll.ticket.domain.review.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.domain.review.dto.ReviewRequest;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.entity.Review;
import com.ll.ticket.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    //리뷰 목록
    public Page<ReviewResponse> getReviewsByConcertId(Long concertId , int page) {

        Pageable pageable = PageRequest.of(page -1, 5); //페이징

       Page<Review> pagingReview = reviewRepository.findByConcertConcertIdOrderByDateDesc(concertId ,pageable);

       return pagingReview.map(ReviewResponse::new);
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
    public ReviewResponse reviewUpdate(ReviewRequest reviewRequest , Long id , Authentication authentication) {

        Review review = findById(id);

        if (!authentication.getName().equals(review.getMember().getEmail()) &&
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 할 수 있는 권한이 없습니다.");
        }
        //작성자 및 관리자는 수정 가능
        review.updateReviewContent(reviewRequest.getContent());

        Review saveReview = reviewRepository.save(review);

       return new ReviewResponse(saveReview);
    }

    @Transactional
    public void deleteById(Long id) {

        reviewRepository.deleteById(id);
    }
}
