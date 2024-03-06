package com.ll.ticket.domain.concert.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping("/concert/{id}")
    public String getConcert(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.findById(id).orElse(null);

        if (concert == null) {
            throw new IllegalArgumentException("존재하지 않는 공연입니다.");
        }

        model.addAttribute("concert", concert);

        return "domain/concert/concert";
    }
}
