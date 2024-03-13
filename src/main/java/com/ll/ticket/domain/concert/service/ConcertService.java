package com.ll.ticket.domain.concert.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import com.ll.ticket.domain.concert.repository.ConcertSeatHistoryRepository;
import com.ll.ticket.global.enums.ConcertStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertDateRepository concertDateRepository;
    private final ConcertSeatHistoryRepository concertSeatHistoryRepository;

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

    public Concert findById(Long id) {
        Optional<Concert> concert = concertRepository.findById(id);
        if (concert.isPresent()) {
            return concert.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 공연입니다.");
        }
    }


    public List<ConcertDate> findConcertDateByConcert(Concert concert) {
        return concertDateRepository.findAllByConcert(concert);
    }

    public Optional<ConcertDate> findConcertDateById(String concertDateId) {
        return concertDateRepository.findById(Long.parseLong(concertDateId));
    }

    public List<Long> findAllSeatNumberByConcertDate(ConcertDate concertDate) {
        List<ConcertSeatHistory> seatHistoryList = concertSeatHistoryRepository.findAllByConcertDate(concertDate);

        List<Long> seatNumbers = seatHistoryList.stream()
                .map(seatHistory -> seatHistory.getSeat().getSeatNumber())
                .collect(Collectors.toList());

        return seatNumbers;
    }

    public void delete(Concert concert) {
        this.concertRepository.delete(concert);
    }
}