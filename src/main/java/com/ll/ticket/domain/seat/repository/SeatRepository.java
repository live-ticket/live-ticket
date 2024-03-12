package com.ll.ticket.domain.seat.repository;

import com.ll.ticket.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
