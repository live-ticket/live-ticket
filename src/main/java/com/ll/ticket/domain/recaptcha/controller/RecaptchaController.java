package com.ll.ticket.domain.recaptcha.controller;

import com.ll.ticket.domain.recaptcha.service.RecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recaptcha")
@RequiredArgsConstructor
public class RecaptchaController {
    private final RecaptchaService recaptchaService;

    @GetMapping
    public String getRecaptcha() {
        return "domain/recaptcha/recaptcha";
    }

    @ResponseBody
    @PostMapping("/validate")
    public boolean validRecaptcha(HttpServletRequest request) {
        String recaptcha = request.getParameter("g-recaptcha-response");
        return recaptchaService.verifyRecaptcha(recaptcha);
    }
}
