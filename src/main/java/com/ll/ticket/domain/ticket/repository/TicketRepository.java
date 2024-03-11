package com.ll.ticket.domain.ticket.repository;

import com.ll.ticket.domain.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
