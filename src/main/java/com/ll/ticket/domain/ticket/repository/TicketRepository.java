package com.ll.ticket.domain.ticket.repository;

import com.ll.ticket.domain.order.entity.Order;
import com.ll.ticket.domain.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByOrder(Order order);
}
