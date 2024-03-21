package com.ll.ticket.domain.concert.service;


import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.repository.ConcertPerformersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertPerformersService {

    private final ConcertPerformersRepository concertPerformersRepository;

    public List<ConcertPerformer> findByConcertConcertId(Long id) {
        List<ConcertPerformer> concertPerformers = concertPerformersRepository.findByConcertConcertId(id);

        if (!concertPerformers.isEmpty()) {
            return concertPerformers;
        } else {
            throw new IllegalArgumentException("해당하는 공연에 출연자가 존재 하지 않습니다 .");
        }
    }
}
