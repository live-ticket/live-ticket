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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeatController {
    private final ConcertService concertService;

    @PostMapping("/concert/{id}/seat")
    public String getSeat(@PathVariable("id") Long id,
                          @RequestParam("concertDateId") String concertDateId,
                          @RequestParam("concertTicketCount") String concertTicketCount,
                          Model model,
                          RedirectAttributes redirectAttributes
    ) {
        try {
            Concert concert = concertService.findById(id);
            ConcertDate concertDate = concertService.findConcertDateById(concertDateId).orElse(null);

            if (concertDate == null) {
                throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
            }

            List<Long> seatIds = concertService.findAllSeatIdByPlace(concert.getPlace());

            if (seatIds.isEmpty()) {
                throw new IllegalArgumentException("공연장에 좌석이 존재하지 않습니다.");
            }

            List<Long> reservedSeatIds = concertService.findAllSeatNumberByConcertDate(concertDate);

            model.addAttribute("concert", concert);
            model.addAttribute("concertDate", concertDate);
            model.addAttribute("reservedSeatIds", reservedSeatIds);
            model.addAttribute("seatIds", seatIds);
            model.addAttribute("concertTicketCount",concertTicketCount);

            return "domain/seat/seat";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }
}
