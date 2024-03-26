package com.ll.ticket.domain.concert.service;

import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.entity.Image;
import com.ll.ticket.domain.concert.repository.ConcertPerformerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertPerformerService {
    private final ConcertPerformerRepository concertPerformerRepository;

    public ConcertPerformer findById(Long id) {
        Optional<ConcertPerformer> concertPerformer = concertPerformerRepository.findById(id);

        if (concertPerformer.isPresent()) {
            return concertPerformer.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 공연자 정보입니다.");
        }
    }

}
