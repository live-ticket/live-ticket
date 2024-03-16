package com.ll.ticket.domain.seat.repository;

import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.seat.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Seat> findAndLockBySeatId(Long seatId);

    Optional<Seat> findById(Long seatId);

    List<Seat> findAllByPlaceOrderBySeatIdAsc(Place place);
}
