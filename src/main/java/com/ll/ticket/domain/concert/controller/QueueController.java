package com.ll.ticket.domain.concert.controller;

import com.ll.ticket.domain.concert.dto.QueueRequestDto;
import com.ll.ticket.domain.concert.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class QueueController {
    private final QueueService queueService;

    @MessageMapping("/queue/addQueue")
    public void addQueue(@RequestBody QueueRequestDto queueRequestDto) {
        queueService.addQueue("대기열", queueRequestDto.getMemberId());
    }
}
