package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
