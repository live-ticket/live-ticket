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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
@ToString
@RequestMapping("/help/question")
public class QuestionController {

    private final QuestionService questionService;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    /**
     * 글 작성 1:1 문의
     */
    @GetMapping("")
    public String questionWrite( WriteRequest writeRequest , Model model) {

        model.addAttribute("writeRequest" , writeRequest);

        return "domain/customer/question/question";
    }

    /**
     * 글 작성 , 이미지 업로드
     */
    @PostMapping("")
    public String questionWrite(@Valid WriteRequest writeRequest  , MultipartFile multipartFile) {

        //Long 반환
         Long questionId =  questionService.createQuestion(writeRequest , multipartFile);

        return "redirect:/help/question/%s".formatted(questionId);
    }

    /**
     * 질문 상세 페이지
     */
    @GetMapping("/{id}")
    public String questionDetail (@PathVariable  Long id , Model model) {

        QuestionResponse questionResponse = questionService.findQuestion(id);

        String fileName = questionResponse.getFileName();
        boolean fileExists = (fileName != null && !fileName.isEmpty()); // 파일이 존재하는지 여부 확인

        model.addAttribute("fileExists", fileExists); //파일이 있으면 파일명을 보여주고 없으면 파일 명을 안보여준다
        model.addAttribute("questionResponse" , questionResponse);

        return "domain/customer/question/questionDetail";
    }
    /**
     * 글 수정
     */
    @GetMapping("/update/{id}")
    public String questionUpdate2(@PathVariable Long id ,UpdateRequest updateRequest , Model model) {

        QuestionResponse questionResponse = questionService.findQuestion(id);

        updateRequest.setQuestionTitle(questionResponse.getQuestionTitle());
        updateRequest.setQuestionContent(questionResponse.getQuestionContent());
        updateRequest.setQuestionCategory(questionResponse.getQuestionCategory());
        updateRequest.setFileName(questionResponse.getFileName());
        updateRequest.setImagePath(questionResponse.getImagePath());

        model.addAttribute("updateRequest" , updateRequest);

        return "domain/customer/question/questionUpdate";
    }

    /**
     *
     * 글 수정
     */
    @PutMapping("/update/{id}")
    public String questionUpdate(@PathVariable Long id , @Valid UpdateRequest updateRequest ,
                                 MultipartFile multipartFile) {

        questionService.updateQuestion(id , updateRequest , multipartFile);

        return "redirect:/help/question/%s".formatted(id);
    }
    /**
     * 글 삭제
     */
    @GetMapping("/delete/{id}")
    public String  questionDelete(@PathVariable Long id ) {

        questionService.deleteQuestion(id);

        return "redirect:/help/myqna";
    }

}
