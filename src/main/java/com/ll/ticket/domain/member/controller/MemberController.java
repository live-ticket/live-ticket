package com.ll.ticket.domain.member.controller;

import com.ll.ticket.domain.member.dto.JoinRequest;
import com.ll.ticket.domain.member.dto.LoginFormDto;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.global.security.config.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;

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

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginFormDto loginFormDto) {
        return "domain/member/loginForm";
    }

    @GetMapping("/login/error")
    public String loginErrorForm(LoginFormDto loginFormDto) {
        return "domain/member/loginForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/revoke")
    public String revoke(HttpServletRequest request, HttpSession session) throws Exception {

        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Object principal = securityContext.getAuthentication().getPrincipal();
        SecurityUser securityUser = (SecurityUser) principal;

        memberService.revokeMember(securityUser);

        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
