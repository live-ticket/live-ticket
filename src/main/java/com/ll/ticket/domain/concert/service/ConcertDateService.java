package com.ll.ticket.domain.concert.service;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertDateService {
    private final ConcertDateRepository concertDateRepository;

    public ConcertDate findById(Long id) {
        Optional<ConcertDate> concertDate = concertDateRepository.findById(id);

        if (concertDate.isPresent()) {
            return concertDate.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 날짜 정보입니다.");
        }
    }
}
