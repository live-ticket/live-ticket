package com.ll.ticket.domain.seat.entity;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.place.entity.Place;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    private Place place;

    private Long seatNumber;
}
