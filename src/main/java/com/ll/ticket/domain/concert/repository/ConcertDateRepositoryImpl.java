package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.ll.ticket.domain.concert.entity.QConcertDate.concertDate1;

@Repository
@RequiredArgsConstructor
public class ConcertDateRepositoryImpl implements ConcertDateRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ConcertDate> findAllByConcertDateInRange(LocalDate startDate, LocalDate endDate) {

        return jpaQueryFactory.selectFrom(concertDate1)
                .where(concertDate1.concertDate.between(startDate, endDate))
                .fetch();
    }
}