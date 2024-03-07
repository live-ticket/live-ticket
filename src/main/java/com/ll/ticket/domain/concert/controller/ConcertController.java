package com.ll.ticket.domain.concert.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping("/concert/{id}")
    public String getConcert(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.findById(id);
        List<ConcertDate> concertDate = concertService.findConcertDateByConcert(concert);

        if (concertDate.isEmpty()) {
            throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
        }

        model.addAttribute("concert", concert);
        model.addAttribute("concertDate", concertDate);

        return "domain/concert/concert";
    }
}
