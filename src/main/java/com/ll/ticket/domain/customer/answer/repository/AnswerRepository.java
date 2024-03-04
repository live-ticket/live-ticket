package com.ll.ticket.domain.customer.answer.repository;

import com.ll.ticket.domain.customer.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {





}
