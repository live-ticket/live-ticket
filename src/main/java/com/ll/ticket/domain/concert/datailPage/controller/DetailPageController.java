package com.ll.ticket.domain.concert.datailPage.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concert")
@RequiredArgsConstructor
public class DetailPageController {

    private final ConcertService concertService;

    @GetMapping("/detail/{id}")
    public String getConcert(@PathVariable Long id , Model model) {

        Concert concert = concertService.findById(id);

        model.addAttribute("concert" , concert);

        return "domain/concert/detailPage/concertDetail";

    }
}
