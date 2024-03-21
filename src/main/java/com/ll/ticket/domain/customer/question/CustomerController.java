package com.ll.ticket.domain.customer.question;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@ToString
public class CustomerController {

    private final QuestionService  questionService;

    @GetMapping("/help/myqna")

    public String cusTomerPage(Model model , Authentication authentication , @RequestParam(value = "page" , defaultValue = "1") int page ) {

       Page<QuestionResponse> questionResponse =  questionService.getQuestion(authentication.getName() , page);

        int nowPage = questionResponse.getPageable().getPageNumber() + 1; // 페이지 0을 1로 설정
        int startPage =  Math.max(1 , ((nowPage - 1) / 5 * 5) + 1 );
        int endPage = Math.min(questionResponse.getTotalPages(), startPage + 4); // 시작 페이지부터 4페이지까지

        log.info("authentication roles: " + authentication.getAuthorities());
        log.info("authentication name :" + authentication.getName());
        log.info("authentication name :" + authentication.getPrincipal());

        model.addAttribute("questionResponse" , questionResponse);
        model.addAttribute("nowPage" , nowPage );
        model.addAttribute("startPage" , startPage );
        model.addAttribute("endPage" , endPage );

        return "domain/customer/customerPage";
    }

    /**
     * 고객센터 접근 시 공지사항페이지
     */
    @GetMapping("/announcement")
    public String announcement(@RequestParam(value = "page" , defaultValue = "1") int page ) {

        return "domain/customer/announcement";

    }

}
