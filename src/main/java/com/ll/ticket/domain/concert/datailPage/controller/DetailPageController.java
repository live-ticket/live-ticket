package com.ll.ticket.domain.concert.datailPage.controller;

import com.ll.ticket.domain.concert.datailPage.detailPagedto.ConcertDTO;
import com.ll.ticket.domain.concert.datailPage.detailPagedto.ConcertDateDTO;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.service.ConcertDateCalService;
import com.ll.ticket.domain.concert.service.ConcertPerformersService;
import com.ll.ticket.domain.concert.service.ConcertService;
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.place.service.PlaceService;
import com.ll.ticket.domain.review.dto.ReviewResponse;
import com.ll.ticket.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private final PlaceService placeService;

    @Value("${kakaoMap.javascript.key}")
    private String mapApiKey;

    @GetMapping("/detail/{id}")
    public String getConcert(@PathVariable Long id , Model model , @RequestParam(value = "page" , defaultValue = "1") int page ) {


        Concert concert = concertService.findById(id);

        ConcertDTO concertDTO = new ConcertDTO(concert);

        List<ConcertDateDTO> concertDateDTO = concertDateCalService.findConcertDateByConcert(concert);

        if (concertDateDTO.isEmpty()) {
            throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
        }

        Duration viewingTime = concertDateCalService.calculateTotalViewingTime(concertDateDTO); // 관람 시간

        List<ConcertPerformer> performers = concertPerformersService.findByConcertConcertId(concertDTO.getConcertId()); //출연자 정보

        Page<ReviewResponse> reviews = reviewService.getReviewsByConcertId(concert.getConcertId() , page); //상세페이지 리뷰 목록

        Place place = placeService.findById(concertDTO.getPlaceId());


        //페이징
        int nowPage = reviews.getPageable().getPageNumber() + 1; // 페이지 0을 1로 설정
        int startPage =  Math.max(1 , ((nowPage - 1) / 5 * 5) + 1 );
        int endPage = Math.min(reviews.getTotalPages(), startPage + 4); // 시작 페이지부터 4페이지까지

        model.addAttribute("place" , place);
        model.addAttribute("performers" , performers);
        model.addAttribute("reviews" , reviews); //리뷰
        model.addAttribute("nowPage" , nowPage );
        model.addAttribute("startPage" , startPage );
        model.addAttribute("endPage" , endPage );
        model.addAttribute("concertDateDTO" , concertDateDTO);
        model.addAttribute("concertDTO" , concertDTO);
        model.addAttribute("viewingTime" , viewingTime);
        model.addAttribute("apiKey" , mapApiKey);

        return "domain/concert/detailPage/concertDetail";
    }


}
