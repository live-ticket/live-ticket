package com.ll.ticket.domain.customer.answer.service;

import com.ll.ticket.domain.customer.answer.dto.AnswerResponse;
import com.ll.ticket.domain.customer.answer.dto.AnswerUpdateRequest;
import com.ll.ticket.domain.customer.answer.dto.AnswerWriteRequest;
import com.ll.ticket.domain.customer.answer.entity.Answer;
import com.ll.ticket.domain.customer.answer.repository.AnswerRepository;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.service.QuestionService;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final  QuestionService questionService;
    private final MemberService memberService;
    private final AnswerRepository answerRepository;
   @Transactional
    public void createAnswer (AnswerWriteRequest answerWriteRequest , Long id ) {

        //  현재 로그인한 사용자의 정보를 가져온다. , 작성자를 포함 하기 위함
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 현재 로그인한 사용자의 이메일
        String authorEmail = authentication.getName();

        Member member = memberService.findByEmail(authorEmail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Question question = questionService.findById(id);

        answerWriteRequest.setMember(member);
        answerWriteRequest.setQuestion(question);
         answerRepository.save(answerWriteRequest.toEntity());

    }

    public AnswerResponse findAnswer(Long id) {
        Answer answer = findById(id) ;
       return new AnswerResponse(answer);
    }

    public Answer findById(Long id ) {

        return answerRepository.findById(id).orElseThrow(() ->

                new IllegalArgumentException("답변을 찾을 수 없습니다. "));
    }

    public void updateAnswer (Long id , AnswerUpdateRequest answerUpdateRequest) {

        Answer answer = findById(id);

        answer.changeAnswer(answerUpdateRequest.getAnswerContent());

    }

    @Transactional
    public void deleteAnswer (Long id)  {

       answerRepository.deleteById(id);
    }

}
