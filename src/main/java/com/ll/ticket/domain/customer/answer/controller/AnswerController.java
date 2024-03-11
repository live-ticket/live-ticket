package com.ll.ticket.domain.customer.answer.controller;

import com.ll.ticket.domain.customer.answer.dto.AnswerWriteRequest;
import com.ll.ticket.domain.customer.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/{id}")
    public String createAnswer(@PathVariable Long id  , AnswerWriteRequest answerWriteRequest) {

        answerService.createAnswer(answerWriteRequest , id);

        return "redirect:/help/question/%s".formatted(id);

    }
}
