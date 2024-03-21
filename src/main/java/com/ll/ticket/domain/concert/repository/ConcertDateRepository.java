package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertDateRepository extends JpaRepository<ConcertDate, Long>, ConcertDateRepositoryCustom {
    List<ConcertDate> findAllByConcert(Concert concert);

}

