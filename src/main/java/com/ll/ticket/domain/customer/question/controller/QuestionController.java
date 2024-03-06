package com.ll.ticket.domain.customer.question.controller;

import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createQuestion( WriteRequest questionDto , Model model) {

        model.addAttribute("questionDto" , questionDto);

        return "domain/customer/question/question";

    }

    /**
     * 글 작성
     */
    @PostMapping("/question")
    public String questionWrite(@Valid WriteRequest writeRequest , MultipartFile multipartFile) {

        // Spring Security를 사용하여 현재 로그인한 사용자의 정보를 가져온다. , 작성자를 포함 하기 위함
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 사용자의 이메일
        String authorEmail = authentication.getName();

        Member member = memberRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        questionService.createQuestion(writeRequest , multipartFile , member);

        return "redirect:/";
    }
}
