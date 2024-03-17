package com.ll.ticket.domain.concert.datailPage.controller;

import com.ll.ticket.domain.concert.datailPage.dto.ConcertDTO;
import com.ll.ticket.domain.concert.datailPage.dto.ConcertDateDTO;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertDateService;
import com.ll.ticket.domain.concert.service.ConcertService;
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
    private final ConcertDateService concertDateService;

    @GetMapping("/detail/{id}")
    public String getConcert(@PathVariable Long id , Model model) {

        Concert concert = concertService.findById(id);

        ConcertDTO concertDTO = new ConcertDTO(concert);

        List<ConcertDateDTO> concertDateDTO = concertDateService.findConcertDateByConcert(concert);


        if (concertDateDTO.isEmpty()) {
            throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
        }

        Duration viewingTime = concertDateService.calculateTotalViewingTime(concertDateDTO);

        model.addAttribute("concertDateDTO" , concertDateDTO);
        model.addAttribute("concertDTO" , concertDTO);
        model.addAttribute("viewingTime" , viewingTime);
        return "domain/concert/detailPage/concertDetail";
    }


}
