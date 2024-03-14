package com.ll.ticket.domain.concert.datailPage.controller;

import com.ll.ticket.domain.concert.datailPage.dto.ConcertDTO;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

@Controller
@RequestMapping("/concert")
@RequiredArgsConstructor
public class DetailPageController {

    private final ConcertService concertService;

    @GetMapping("/detail/{id}")
    public String getConcert(@PathVariable Long id , Model model) {

        Concert concert = concertService.findById(id);

        ConcertDTO concertDTO = new ConcertDTO(concert);

        Duration viewingTime = concertDTO.calculateViewingTime(); //startTime , EndTime 연산 하여 관람 시간을 계산

        model.addAttribute("concertDTO" , concertDTO);
        model.addAttribute("viewingTime" , viewingTime);
        return "domain/concert/detailPage/concertDetail";

    }


}