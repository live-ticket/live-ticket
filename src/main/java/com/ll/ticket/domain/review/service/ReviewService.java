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


}
