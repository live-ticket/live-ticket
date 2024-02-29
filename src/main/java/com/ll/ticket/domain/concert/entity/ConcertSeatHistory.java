package com.ll.ticket.domain.concert.entity;

import com.ll.ticket.domain.seat.entity.Seat;
import com.ll.ticket.domain.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ConcertSeatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertSeatHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_date_id")
    private ConcertDate concertDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
