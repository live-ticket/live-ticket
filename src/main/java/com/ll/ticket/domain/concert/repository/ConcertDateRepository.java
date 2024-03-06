package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDateRepository extends JpaRepository<ConcertDate, Long>, ConcertDateRepositoryCustom {
}
