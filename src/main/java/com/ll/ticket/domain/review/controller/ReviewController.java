package com.ll.ticket.domain.review.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import com.ll.ticket.domain.review.dto.ReviewRequest;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.entity.Review;
import com.ll.ticket.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ConcertService concertService;
    private final ReviewService reviewService;

    @PostMapping("/{id}")
    public String reviewWrite(@PathVariable Long id , ReviewRequest reviewWriteRequest) {

        Concert concert =  concertService.findById(id);

        Long responseId =   reviewService.reviewCreate(reviewWriteRequest , concert);

        return "redirect:/concert/detail/%s".formatted(responseId);
    }

    @PatchMapping("/update/{id}")
    @ResponseBody
    public ReviewResponse reviewUpdate(@PathVariable Long id , @RequestBody  ReviewRequest reviewRequest , Authentication authentication) {

        return reviewService.reviewUpdate(reviewRequest , id , authentication);

    }

    @GetMapping("/delete/{id}")
    public String reviewDelete(@PathVariable("id") Long id , Authentication authentication) {

        Review review = reviewService.findById(id);

        // 작성자 또는 관리자인 경우에만 삭제 권한이 있음
        if (!authentication.getName().equals(review.getMember().getEmail()) &&
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 할 수 있는 권한이 없습니다.");
        }
        //작성자 및 관리자는 삭제 가능
        reviewService.deleteById(id);

        return "redirect:/concert/detail/%s".formatted(review.getConcert().getConcertId());
    }


}
