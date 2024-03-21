package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {

    Page<Question> findByMemberEmail(String email , Pageable pageable);
    Page<Question>findAll(Pageable pageable);
}
