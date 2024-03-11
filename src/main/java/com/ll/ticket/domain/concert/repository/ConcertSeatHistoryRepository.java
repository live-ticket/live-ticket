package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertSeatHistoryRepository extends JpaRepository<ConcertSeatHistory, Long> {
}
