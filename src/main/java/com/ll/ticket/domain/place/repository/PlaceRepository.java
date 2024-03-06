package com.ll.ticket.domain.place.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
