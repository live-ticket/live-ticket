package com.ll.ticket.domain.customer.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/help")
    public String cosTomerPage() {

        return "domain/customer/customerPage";
    }
}
