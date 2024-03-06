package com.ll.ticket.domain.customer.question.controller;

import com.ll.ticket.domain.customer.question.dto.QuestionDto;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
@ToString
public class QuestionController {

    private final QuestionService questionService;
    /**
     * 글 작성
     */
    @GetMapping("/question")
    public String createQuestion(QuestionDto questionDto , Model model) {

        model.addAttribute("questionDto" , questionDto);

        return "domain/customer/question/question";

    }

    /**
     * 글 작성
     */
    @PostMapping("/question")
    public String questionWrite(@Valid QuestionDto questionDto){

        questionService.createQuestion(questionDto);

        return "redirect:/";
    }
}
