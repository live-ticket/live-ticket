package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.answer.dto.AnswerDto;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@Transactional
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @DisplayName("질문 생성")
    void createQuestion() {
        Member member = Member.builder()
                .userId(1L)
                .name("테스트")
                .build();
        Member saveMember = memberRepository.save(member);

        List<AnswerDto> answerList = Arrays.asList(
                AnswerDto.builder().answerContent("답변 내용 1")
                        .memberName(saveMember.getName())
                        .build(),
                AnswerDto.builder()
                        .answerContent("답변 내용 2")
                        .memberName(saveMember.getName())
                        .build()
        );


        /**
         *      테스트 잠시 대기
         */

    }
}