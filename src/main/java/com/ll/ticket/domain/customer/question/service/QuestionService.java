package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    /**
     * 아이디 Question Entity id 값 반환
     */
   @Transactional
    public Long createQuestion(WriteRequest writeRequest) {

       // Spring Security를 사용하여 현재 로그인한 사용자의 정보를 가져옴
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       // 현재 로그인한 사용자의 이메일
       String authorEmail  = authentication.getName();

       //로그인 멤버를 찾는다.
       Member member = memberRepository.findByEmail(authorEmail)
               .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

       return questionRepository.save(writeRequest.toEntity(member)).getCustomerQId();

   }




}


