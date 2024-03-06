package com.ll.ticket.domain.concert.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import com.ll.ticket.global.enums.ConcertStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertDateRepository concertDateRepository;

    @Transactional
    public void changeStatus(LocalDate todayDate) {
        LocalDate enableStartDate = todayDate.minusDays(1);
        LocalDate ableStartDate = todayDate.plusDays(2);
        LocalDate ableEndDate = todayDate.plusWeeks(1);

        changeConcertStatusInRange(enableStartDate, todayDate, ConcertStatus.ENABLE);
        changeConcertStatusInRange(ableStartDate, ableEndDate, ConcertStatus.ABLE);
    }

    private void changeConcertStatusInRange(LocalDate startDate, LocalDate endDate, ConcertStatus status) {
        concertDateRepository.findAllByConcertDateInRange(startDate, endDate).forEach(concertDate -> {
            concertDate.getConcert().setStatus(status);
        });
    }

    public Optional<Concert> findById(Long id) {
        return concertRepository.findById(id);
    }
}
