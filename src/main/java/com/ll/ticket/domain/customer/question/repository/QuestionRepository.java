package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question , Long> {

    List<Question> findByMemberEmail(String email);
}
