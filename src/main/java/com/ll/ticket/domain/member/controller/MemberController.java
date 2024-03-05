package com.ll.ticket.domain.member.controller;

import com.ll.ticket.domain.member.dto.JoinRequest;
import com.ll.ticket.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute JoinRequest joinRequest, Model model) {
        model.addAttribute("joinRequest", joinRequest);
        return "domain/member/joinForm";
    }

    @PostMapping("/join")
    public String postJoinForm(@Valid @ModelAttribute JoinRequest joinRequest,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "domain/member/joinForm";
        }

        try {
            memberService.join(joinRequest);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("signupFormDto", joinRequest);
            return "domain/member/joinForm";
        }

        return "redirect:/members/login";
    }

}
