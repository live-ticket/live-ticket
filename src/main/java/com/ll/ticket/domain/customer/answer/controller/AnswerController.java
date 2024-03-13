package com.ll.ticket.domain.customer.answer.controller;

import com.ll.ticket.domain.customer.answer.dto.AnswerResponse;
import com.ll.ticket.domain.customer.answer.dto.AnswerUpdateRequest;
import com.ll.ticket.domain.customer.answer.dto.AnswerWriteRequest;
import com.ll.ticket.domain.customer.answer.service.AnswerService;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService  questionService;
    @PostMapping("/{id}")
    public String createAnswer(@PathVariable Long id  , @Valid AnswerWriteRequest answerWriteRequest , Model model) {

        model.addAttribute("answerWriteRequest" , answerWriteRequest);


        Long answerQid = answerService.createAnswer(answerWriteRequest, id);



        return "redirect:/help/question/%s".formatted(answerQid);

    }

    @GetMapping("/update/{id}")
    public String getAnswerUpdate(@PathVariable Long id , AnswerUpdateRequest answerUpdateRequest ,  Model model) {

        AnswerResponse answerResponse = answerService.findAnswer(id);

        answerUpdateRequest.setAnswerContent(answerResponse.getAnswerContent());

        model.addAttribute("answerResponse", answerResponse);

        return "domain/customer/answer/answerUpdate";
    }

    @PutMapping("update/{id}")
    public String answerUpdate(@PathVariable Long id , @Valid AnswerUpdateRequest answerUpdateRequest ) {

         Long questionId =  answerService.updateAnswer(id , answerUpdateRequest);

        return "redirect:/help/question/%s".formatted(questionId);
    }

    @GetMapping("delete/{id}")
    public String answerDelete(@PathVariable Long id) {

       AnswerResponse answerResponse = answerService.findAnswer(id);

        answerService.deleteAnswer(id);

        return  "redirect:/help/question/%s".formatted(answerResponse.getCustomerQId());
    }


}
