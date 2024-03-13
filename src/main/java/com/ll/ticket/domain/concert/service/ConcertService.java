package com.ll.ticket.domain.concert.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import com.ll.ticket.global.enums.ConcertStatus;
import com.ll.ticket.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertDateRepository concertDateRepository;
    private final ConcertRepository concertRepository;

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

    public Page<Concert> getConcertList(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return this.concertRepository.findAll(pageable);
    }

    public Concert getConcert(Long id){
        Optional<Concert> concert = this.concertRepository.findById(id);

        if (concert.isPresent()) {
            return concert.get();
        } else {
            throw new DataNotFoundException("concert not found");
        }
    }

    public void delete(Concert concert) {
        this.concertRepository.delete(concert);
    }
}