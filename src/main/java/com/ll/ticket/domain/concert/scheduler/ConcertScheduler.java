package com.ll.ticket.domain.concert.scheduler;

import com.ll.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class ConcertScheduler {
    private final ConcertService concertService;
    @Scheduled(cron = "0 0 * * * *") // 운영-매일 0시
//    @Scheduled(cron = "0 * * * * *") // 테스트용-매분 0초
    public void runConcertChangeStatus() {
         LocalDate todayDate = LocalDate.now();

         concertService.changeStatus(todayDate);
    }
}
