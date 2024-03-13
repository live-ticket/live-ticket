package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question , Long> , QuestionRepositoryCustom{

}
