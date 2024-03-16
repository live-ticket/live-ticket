package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import com.ll.ticket.domain.seat.entity.Seat;
import com.ll.ticket.domain.ticket.entity.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface ConcertSeatHistoryRepository extends JpaRepository<ConcertSeatHistory, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ConcertSeatHistory> findAllAndLockByConcertDate(ConcertDate concertDate);

    List<ConcertSeatHistory> findAllByConcertDate(ConcertDate concertDate);

    List<ConcertSeatHistory> findAllBySeatAndConcertDate(Seat seat, ConcertDate concertDate);

    Iterable<ConcertSeatHistory> findAllBySeatAndConcertDateAndTicket(Seat seat, ConcertDate concertDate, Ticket ticket);
}
