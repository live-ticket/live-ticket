package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question , Long> {


}
