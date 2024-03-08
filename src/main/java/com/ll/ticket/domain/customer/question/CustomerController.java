package com.ll.ticket.domain.customer.question;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @GetMapping("/myqna")
    @PreAuthorize("isAuthenticated()") //로그인 사용자
    public String cosTomerPage(Model model , Authentication authentication) {
       List<QuestionResponse> questionResponse =  questionService.getQuestion(authentication.getName());

       model.addAttribute("questionResponse" , questionResponse);

        return "domain/customer/customerPage";
    }

    /**
     * 고객센터 접근 시 공지사항페이지 로 리다이렉트
     */
    @GetMapping("/announcement")
    public String announcement() {

        return "domain/customer/customerPage";

    }

}
