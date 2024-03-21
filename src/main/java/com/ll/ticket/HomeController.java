package com.ll.ticket;

import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() == "anonymous") {
            return "redirect:/login";
        }
        Optional<Member> _member = memberService.findByEmail(authentication.getName());
        if (_member.isEmpty()) {
            model.addAttribute("loginUser", false);
            model.addAttribute("member", null);
        } else {
            model.addAttribute("loginUser", true);
            model.addAttribute("member", _member.get());
        }
        return "main";
    }
}
