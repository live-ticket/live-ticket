package com.ll.ticket.domain.customer.question;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class CustomerController {

    private final QuestionService  questionService;
    @GetMapping("")
    public String cosTomerPage(Model model) {
       List<QuestionResponse> questionResponse =  questionService.getQuestion();

       model.addAttribute("questionResponse" , questionResponse);

        return "domain/customer/customerPage";
    }

}
