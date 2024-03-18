package com.ll.ticket.domain.place.service;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Place findById(Long id) {
        Optional<Place> place = placeRepository.findById(id);

        if (place.isPresent()) {
            return place.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 장소 정보입니다.");
        }
    }
}
