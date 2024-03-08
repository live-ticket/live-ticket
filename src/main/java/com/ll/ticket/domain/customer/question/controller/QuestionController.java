package com.ll.ticket.domain.customer.question.controller;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.dto.UpdateRequest;
import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import com.ll.ticket.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
@ToString
@RequestMapping("/customer")
public class QuestionController {

    private final QuestionService questionService;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    /**
     * 글 작성
     */
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/question")
    public String questionWrite( WriteRequest writeRequest , Model model) {

        model.addAttribute("writeRequest" , writeRequest);

        return "domain/customer/question/question";

    }

    /**
     * 글 작성 , 이미지 업로드
     */
//    @PreAuthorize("isAuthenticated()")
    @PostMapping("/question")
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
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/question/{id}")
    public String questionDetail (@PathVariable Long id , Model model) {

        QuestionResponse questionResponse = questionService.findQuestion(id);

        model.addAttribute("questionResponse" , questionResponse);

        return "domain/customer/question/questionDetail";
    }
    /**
     * 글 수정
     */
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/question/update/{id}")
    public String questionUpdate2(@PathVariable Long id ,UpdateRequest updateRequest) {

        QuestionResponse questionResponse = questionService.findQuestion(id); //응답 DTO 를 Model 로 넘김

        updateRequest.setQuestionTitle(questionResponse.getQuestionTitle());
        updateRequest.setQuestionContent(questionResponse.getQuestionContent());
        updateRequest.setQuestionCategory(updateRequest.getQuestionCategory());

        return "domain/customer/question/questionUpdate";
    }

    /**
     *
     * 글 수정
     */
//    @PreAuthorize("isAuthenticated()")
    @PostMapping("/question/update/{id}")
    public String questionUpdate(@PathVariable Long id , @Valid UpdateRequest updateRequest ,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/customer/question/questionUpdate";
        }
        questionService.updateQuestion(id , updateRequest);

        return "redirect:/customer/question/%s".formatted(id);
    }
    /**
     * 글 삭제
     */
    @GetMapping("/question/delete/{id}")
    public String  questionDelete(@PathVariable Long id) {

        questionService.deleteQuestion(id);

        return "redirect:/help";
    }

}
