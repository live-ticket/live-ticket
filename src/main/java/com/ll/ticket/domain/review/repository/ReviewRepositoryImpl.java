package com.ll.ticket.domain.review.repository;

import com.ll.ticket.domain.review.entity.QReview;
import com.ll.ticket.domain.review.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> findByConcertConcertIdOrderByDateDesc(Long concertId) {

        QReview qReview = QReview.review;

        return jpaQueryFactory.selectFrom(qReview)
                .where(qReview.concert.concertId.eq(concertId))
                .orderBy(qReview.createDate.desc())
                .fetch();
    }
}
