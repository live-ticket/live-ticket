package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.customer.question.entity.QQuestion;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 내가 작성한 글만 볼수 있다. 최신 순 으로
     */
    @Override
     public Page<Question> findByMemberEmail(String email , Pageable pageable) {

        QQuestion qQuestion = QQuestion.question;

        QueryResults<Question> results = jpaQueryFactory.selectFrom(qQuestion)
                .where(qQuestion.member.email.eq(email))
                .orderBy(qQuestion.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Question> questions = results.getResults();

        long total = results.getTotal();

        return new PageImpl<>(questions, pageable, total);
    }

    @Override
    public Page<Question>findAll(Pageable pageable) {

        QQuestion qQuestion = QQuestion.question;

        QueryResults<Question> results =  jpaQueryFactory.selectFrom(qQuestion)
                .orderBy(qQuestion.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Question> questions = results.getResults();

        long total = results.getTotal();

        return new PageImpl<>(questions, pageable,total);
    }
}
