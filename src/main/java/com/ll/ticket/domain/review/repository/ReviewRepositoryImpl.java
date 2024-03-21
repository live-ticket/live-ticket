package com.ll.ticket.domain.review.repository;

import com.ll.ticket.domain.review.entity.QReview;
import com.ll.ticket.domain.review.entity.Review;
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
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findByConcertConcertIdOrderByDateDesc(Long concertId , Pageable pageable) {

        QReview qReview = QReview.review;

        QueryResults<Review> results = jpaQueryFactory.selectFrom(qReview)
                .where(qReview.concert.concertId.eq(concertId))
                .orderBy(qReview.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Review> reviews = results.getResults();

        long total = results.getTotal();
        return new PageImpl<>(reviews , pageable , total);
    }

}
