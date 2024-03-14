package com.ll.ticket.domain.seat.repository;

import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByPlaceOrderBySeatIdAsc(Place place);
}
