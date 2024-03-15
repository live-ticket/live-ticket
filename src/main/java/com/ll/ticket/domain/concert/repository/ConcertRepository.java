package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Page<Concert> findAll(Pageable pageable);
}