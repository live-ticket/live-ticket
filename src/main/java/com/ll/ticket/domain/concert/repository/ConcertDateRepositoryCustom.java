package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertDate;

import java.time.LocalDate;
import java.util.List;

public interface ConcertDateRepositoryCustom {
    List<ConcertDate> findAllByConcertDateInRange(LocalDate startDate, LocalDate endDate);
}
