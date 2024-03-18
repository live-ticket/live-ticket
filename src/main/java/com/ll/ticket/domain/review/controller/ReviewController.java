package com.ll.ticket.domain.review.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import com.ll.ticket.domain.review.dto.ReviewRequest;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    @ResponseBody
    public ReviewResponse reviewUpdate(@PathVariable Long id , @RequestBody  ReviewRequest reviewRequest) {

        return reviewService.reviewUpdate(reviewRequest , id);

    }



}
