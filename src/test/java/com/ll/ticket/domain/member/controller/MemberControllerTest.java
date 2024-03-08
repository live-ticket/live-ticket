package com.ll.ticket.domain.member.controller;

import com.ll.ticket.domain.member.dto.JoinRequest;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.global.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    MemberService memberService;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Test
    void login() throws Exception {
//        JoinRequest joinRequest = new JoinRequest();
//        joinRequest.setName("스프링테스트");
//        joinRequest.setEmail("test1234@naver.com");
//        joinRequest.setPassword("test1234@");
//        joinRequest.setPasswordConfirm("test1234@");
//        joinRequest.setBirthDay(LocalDate.now());
//        joinRequest.setPhoneNumber("010-2638-1927");
//        joinRequest.setGender(Gender.MALE);
//        memberService.join(joinRequest);

        mockMvc.perform(post("/members/login")
                        .param("email", "test1234@naver.com")
                        .param("password", "test1234@")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("test1234@naver.com"))
        ;
    }

}