package com.ll.ticket.domain.customer.question.controller;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.dto.UpdateRequest;
import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import com.ll.ticket.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
@ToString
public class QuestionController {

    private final QuestionService questionService;
    private final MemberRepository memberRepository;

    /**
     * 글 작성
     */
    @GetMapping("/question")
    @PreAuthorize("isAuthenticated()")
    public String questionWrite( WriteRequest writeRequest , Model model) {

        model.addAttribute("writeRequest" , writeRequest);

        return "domain/customer/question/question";

    }

    /**
     * 글 작성 , 이미지 업로드
     */
    @PostMapping("/question")
    @PreAuthorize("isAuthenticated()")
    public String questionWrite(@Valid WriteRequest writeRequest , BindingResult bindingResult , MultipartFile multipartFile ) {

        if (bindingResult.hasErrors()) {
            return "domain/customer/question/question";
        }

        questionService.createQuestion(writeRequest , multipartFile );

        return "redirect:/";
    }

    /**
     * 질문 상세 페이지
     */
    @GetMapping("/question/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionDetail (@PathVariable Long id , Model model) {

        QuestionResponse questionResponse = questionService.findQuestion(id);

        model.addAttribute("questionResponse" , questionResponse);

        return "domain/customer/question/questionDetail";
    }
    // http://127.0.0.1:8080/customer/question/update/4
    @GetMapping("/question/update/{id}")
    public String questionUpdate2(@PathVariable Long id , UpdateRequest updateRequest) {

        QuestionResponse questionResponse = questionService.findQuestion(id);

        updateRequest.setQuestionTitle(questionResponse.getQuestionTitle());
        updateRequest.setQuestionContent(questionResponse.getQuestionContent());
        updateRequest.setQuestionCategory(questionResponse.getQuestionCategory());

        return "domain/customer/question/questionUpdate";
    }

    @PostMapping("/question/update/{id}")
    public String questionUpdate(@PathVariable Long id , UpdateRequest updateRequest) {

        questionService.updateQuestion(id , updateRequest);

        return "redirect:/question/detail/%s".formatted(id);
    }
}
