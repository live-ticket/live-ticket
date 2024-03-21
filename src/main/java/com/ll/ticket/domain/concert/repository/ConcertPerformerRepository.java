package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertPerformerRepository extends JpaRepository<ConcertPerformer, Long> {

}
