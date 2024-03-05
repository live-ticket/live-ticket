package com.ll.ticket.domain.customer.answer.service;

import com.ll.ticket.domain.customer.answer.dto.AnswerDto;
import com.ll.ticket.domain.customer.answer.entity.Answer;
import com.ll.ticket.domain.customer.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    public void createAnswer (AnswerDto answerDto) {

        Answer answer =  Answer.toEntity(answerDto);

        answerRepository.save(answer);

    }

}
