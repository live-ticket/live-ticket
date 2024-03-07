package com.ll.ticket.domain.customer.question.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String createQuestion( WriteRequest writeRequest , Model model) {

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
}
