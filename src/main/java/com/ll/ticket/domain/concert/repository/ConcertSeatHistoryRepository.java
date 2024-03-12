package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSeatHistoryRepository extends JpaRepository<ConcertSeatHistory, Long> {
    List<ConcertSeatHistory> findAllByConcertDate(ConcertDate concertDate);
}
