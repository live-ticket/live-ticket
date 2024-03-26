//package com.ll.ticket.domain.member.controller;
//
//import com.ll.ticket.domain.member.dto.JoinRequest;
//import com.ll.ticket.domain.member.repository.MemberRepository;
//import com.ll.ticket.domain.member.service.MemberService;
//import com.ll.ticket.global.enums.Gender;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
//import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class MemberControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//
//    // 이 변수에 저장된 값은 beforeEach에서 생성한 회원의 email 값입니다.
//    private static final String TEST_MEMBER_EMAIL = "test1234@naver.com";
//    private static final String TEST_MEMBER_PASSWORD = "test1234@";
//
//    @BeforeEach
//    void beforeEach() {
//        JoinRequest joinRequest = new JoinRequest();
//        joinRequest.setName("스프링테스트");
//        joinRequest.setEmail(TEST_MEMBER_EMAIL);
//        joinRequest.setPassword(TEST_MEMBER_PASSWORD);
//        joinRequest.setPasswordConfirm(TEST_MEMBER_PASSWORD);
//        joinRequest.setBirthDay(LocalDate.now());
//        joinRequest.setPhoneNumber("010-8888-9999");
//        joinRequest.setGender(Gender.MALE);
//        memberService.join(joinRequest);
//    }
//
//    @AfterEach
//    void afterEach() {
//        memberRepository.findByEmail(TEST_MEMBER_EMAIL)
//                .ifPresent(memberRepository::delete);
//    }
//
//    @DisplayName("로그인 성공")
//    @Test
//    void login() throws Exception {
//        mockMvc.perform(post("/members/login")
//                        .param("email", TEST_MEMBER_EMAIL)
//                        .param("password", TEST_MEMBER_PASSWORD)
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"))
//                .andExpect(authenticated().withUsername("test1234@naver.com"))
//        ;
//    }
//
//    @DisplayName("로그인 실패")
//    @Test
//    void login_fail() throws Exception {
//        mockMvc.perform(post("/members/login")
//                        .param("email", "11111111@naver.com")
//                        .param("password", TEST_MEMBER_PASSWORD)
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/members/login?error"))
//                .andExpect(unauthenticated())
//        ;
//    }
//
//    @DisplayName("로그아웃")
//    @Test
//    void logout() throws Exception {
//        mockMvc.perform(post("/members/logout")
//                        .param("email", TEST_MEMBER_EMAIL)
//                        .param("password", TEST_MEMBER_PASSWORD)
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"))
//                .andExpect(unauthenticated());
//    }
//}