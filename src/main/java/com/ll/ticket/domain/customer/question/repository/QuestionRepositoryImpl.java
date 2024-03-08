package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.QQuestion;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
     public List<Question>findByMemberEmail(String email) {

        QQuestion qQuestion = QQuestion.question;

        return jpaQueryFactory.selectFrom(qQuestion)
                .where(qQuestion.member.email.eq(email))
                .orderBy(qQuestion.createDate.desc())
                .fetch();
    }


}
