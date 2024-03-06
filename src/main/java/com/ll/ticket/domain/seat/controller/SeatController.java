package com.ll.ticket.domain.seat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SeatController {
    @GetMapping("/concert/{id}/seat")
    public String getSeat(@PathVariable("id") Long id) {
        return "domain/seat/seat";
    }
}
