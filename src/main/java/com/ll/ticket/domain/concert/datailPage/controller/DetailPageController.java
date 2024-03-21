package com.ll.ticket.domain.concert.datailPage.controller;

import com.ll.ticket.domain.concert.datailPage.detailPagedto.ConcertDTO;
import com.ll.ticket.domain.concert.datailPage.detailPagedto.ConcertDateDTO;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.service.ConcertDateCalService;
import com.ll.ticket.domain.concert.service.ConcertPerformersService;
import com.ll.ticket.domain.concert.service.ConcertService;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.util.List;

@Controller
@RequestMapping("/concert")
@RequiredArgsConstructor
public class DetailPageController {

    private final ConcertService concertService;
    private final ConcertDateCalService concertDateCalService;
    private  final ReviewService reviewService;
    private final ConcertPerformersService concertPerformersService;

    @GetMapping("/detail/{id}")
    public String getConcert(@PathVariable Long id , Model model) {


        Concert concert = concertService.findById(id);

        ConcertDTO concertDTO = new ConcertDTO(concert);

        List<ConcertDateDTO> concertDateDTO = concertDateCalService.findConcertDateByConcert(concert);

        if (concertDateDTO.isEmpty()) {
            throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
        }

        Duration viewingTime = concertDateCalService.calculateTotalViewingTime(concertDateDTO);

        List<ReviewResponse> reviews = reviewService.getReviewsByConcertId(concert.getConcertId()); //상세페이지 리뷰 목록

        List<ConcertPerformer> performers = concertPerformersService.findByConcertConcertId(concertDTO.getConcertId());

        model.addAttribute("performers" , performers);
        model.addAttribute("reviews" , reviews); //리뷰
        model.addAttribute("concertDateDTO" , concertDateDTO);
        model.addAttribute("concertDTO" , concertDTO);
        model.addAttribute("viewingTime" , viewingTime);
        return "domain/concert/detailPage/concertDetail";
    }


}
