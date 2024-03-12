package com.ll.ticket.domain.customer.answer.controller;

import com.ll.ticket.domain.customer.answer.dto.AnswerResponse;
import com.ll.ticket.domain.customer.answer.dto.AnswerUpdateRequest;
import com.ll.ticket.domain.customer.answer.dto.AnswerWriteRequest;
import com.ll.ticket.domain.customer.answer.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/{id}")
    public String createAnswer(@PathVariable Long id  , @Valid  AnswerWriteRequest answerWriteRequest) {

      answerService.createAnswer(answerWriteRequest , id);

        return "redirect:/help/question/%s".formatted(id);

    }

    @GetMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<AnswerResponse> getAnswerUpdateForm(@PathVariable Long id) {

        AnswerResponse answerResponse = answerService.findAnswer(id);
        return ResponseEntity.ok(answerResponse);
    }

//    @GetMapping("/update/{id}")
//    public String answerUpdate (@PathVariable Long id , AnswerUpdateRequest answerUpdateRequest , Model model) {
//
//       Answer answer = answerService.findById(id);
//
//       answerUpdateRequest.setAnswerContent(answer.getAnswerContent());
//
//       model.addAttribute("answerUpdateRequest" , answerUpdateRequest);
//
//        return "domain/customer/answer/answerUpdate";
//    }

    @PostMapping("update/{id}")
    public String answerUpdate2(@PathVariable Long id ,  @Valid  AnswerUpdateRequest answerUpdateRequest) {

      answerService.updateAnswer(id , answerUpdateRequest);

    return "redirect:/help/question/%s".formatted(id);

    }

    @GetMapping("delete/{id}")
    public String answerDelete(@PathVariable Long id) {

        answerService.deleteAnswer(id);

        return  "redirect:/help/question/%s".formatted(id);
    }
}
