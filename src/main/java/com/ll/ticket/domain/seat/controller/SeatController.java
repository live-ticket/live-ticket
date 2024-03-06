package com.ll.ticket.domain.seat.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SeatController {
    private final ConcertService concertService;

    @PostMapping("/concert/{id}/seat")
    public String getSeat(@PathVariable("id") Long id, @RequestParam("concertDateId") String concertDateId, Model model) {
        Concert concert = concertService.findById(id).orElse(null);
        ConcertDate concertDate = concertService.findConcertDateById(concertDateId).orElse(null);

        if (concert == null) {
            throw new IllegalArgumentException("존재하지 않는 공연입니다.");
        }

        if (concertDate == null) {
            throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
        }

        model.addAttribute("concert", concert);
        model.addAttribute("concertDate", concertDate);

        return "domain/seat/seat";
    }
}
