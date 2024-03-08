package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.Question;

import java.util.List;

public interface QuestionRepositoryCustom {

    List<Question> findByMemberEmail(String email);
}
