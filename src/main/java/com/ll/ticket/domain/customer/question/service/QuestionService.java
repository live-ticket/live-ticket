package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.dto.QuestionDto;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

   @Transactional
    public Long createQuestion(QuestionDto questionDto) {

       return questionRepository.save(questionDto.toEntity()).getCustomerQId();

   }


}


