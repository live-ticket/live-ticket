package com.ll.ticket.domain.member.mypage.controller;

import com.ll.ticket.domain.member.dto.ModifyRequest;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.global.security.authentication.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MemberService memberService;
    @GetMapping("/profile")
    public String profileForm(@AuthenticationPrincipal UserPrincipal userPrincipal, ModifyRequest modifyRequest) {

        Member member = userPrincipal.getMember();

        modifyRequest.setEmail(member.getEmail());
        modifyRequest.setName(member.getName());
        modifyRequest.setPhoneNumber(member.getPhoneNumber());
        modifyRequest.setBirthday(member.getBirthDay());
        modifyRequest.setGender(member.getGender());

        return "domain/mypage/profile";

    }

    @PostMapping("/profile")
    public String modify(@AuthenticationPrincipal UserPrincipal userPrincipal,
                         @Valid ModifyRequest modifyRequest,
                         BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "domain/mypage/profile";
        }


        if (StringUtils.hasText(modifyRequest.getPassword())) { //변경하고자 하는 비밀번호가 입력되어있을때
            if (!memberService.confirmPassword(modifyRequest.getPassword(), modifyRequest.getPasswordConfirm())) {
                bindingResult.reject("passwordIncorrect", "비밀번호가 일치하지 않습니다.");
                return "domain/mypage/profile";
            }
        }


        if (memberService.existsByPhoneNumber(String.valueOf(modifyRequest.getPhoneNumber())) &&
                !Objects.equals(userPrincipal.getMember().getPhoneNumber(), modifyRequest.getPhoneNumber())
        ) {
            bindingResult.reject("existPhoneNumber", "이미 존재하는 전화번호입니다.");
            return "domain/mypage/profile";
        }

        memberService.modify(modifyRequest, userPrincipal.getMember());

        return "redirect:/mypage/profile";
    }

}
