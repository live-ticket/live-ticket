//package com.ll.ticket.domain.customer.question.service;
//
//import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
//import com.ll.ticket.domain.customer.question.dto.WriteRequest;
//import com.ll.ticket.domain.customer.question.entity.Question;
//import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
//import com.ll.ticket.domain.member.entity.Member;
//import com.ll.ticket.domain.member.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
//@Transactional
//class QuestionServiceTest {
//
//    @Autowired
//    private QuestionService questionService;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//
//    @Test
//    @DisplayName("질문 생성")
//    void createQuestion() {
////        Member member = Member.builder()
////                .userId(1L)
////                .name("테스트")
////                .build();
////        Member saveMember = memberRepository.save(member);
////
////        List<AnswerDto> answerList = Arrays.asList(
////                AnswerDto.builder().answerContent("답변 내용 1")
////                        .memberName(saveMember.getName())
////                        .build(),
////                AnswerDto.builder()
////                        .answerContent("답변 내용 2")
////                        .memberName(saveMember.getName())
////                        .build()
////        );
//
//    }
//
//    @Test
//    @DisplayName("질문 찾기")
//    void modifyQuestion() {
//        Member member = Member.builder()
//                .userId(1L)
//                .name("테스트")
//                .build();
//        Member saveMember = memberRepository.save(member);
//
//        WriteRequest writeRequest = WriteRequest. builder()
//                .customerQId(1L)
//                .questionTitle("하하")
//                .questionContent("호호")
//                .member(saveMember)
//               .build();
//        Question question =questionRepository.save(writeRequest.toEntity());
//
//        QuestionResponse questionResponse = questionService.findQuestion(question.getCustomerQId());
//
//
//        assertThat(questionResponse.getCustomerQId()).isEqualTo(1L);
//        assertThat(questionResponse.getAuthorId()).isEqualTo(1L);
//
//
//    }
//}