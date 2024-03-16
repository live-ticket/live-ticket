package com.ll.ticket.domain.order.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import com.ll.ticket.domain.concert.repository.ConcertSeatHistoryRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import com.ll.ticket.domain.order.entity.Order;
import com.ll.ticket.domain.order.repository.OrderRepository;
import com.ll.ticket.domain.seat.entity.Seat;
import com.ll.ticket.domain.seat.repository.SeatRepository;
import com.ll.ticket.domain.ticket.entity.Ticket;
import com.ll.ticket.domain.ticket.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
public class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ConcertRepository concertRepository;
    @Autowired
    ConcertDateRepository concertDateRepository;
    @Autowired
    ConcertSeatHistoryRepository concertSeatHistoryRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    OrderRepository orderRepository;

    private int threadCount;
    private Member member;
    private Concert concert;
    private ConcertDate concertDate;
    private String selectedSeatsData;
    private Seat seat;
    private Order order1;
    private Order order2;

    @AfterEach
    void afterEach() {
        deleteOrder(order1);
        deleteOrder(order2);
    }

    private void deleteOrder(Order order) {
        if (order != null) {
            Ticket ticket = ticketRepository.findByOrder(order);
            concertSeatHistoryRepository.findAllBySeatAndConcertDateAndTicket(seat, concertDate, ticket)
                    .forEach(concertSeatHistoryRepository::delete);
            ticketRepository.delete(ticket);
            orderRepository.findById(order.getOrderId())
                    .ifPresent(orderRepository::delete);
        }
    }

    @Test
    @DisplayName("주문 동시성 제어 테스트")
    void order() throws InterruptedException {
        // Given
        this.threadCount = 2;
        this.member = memberRepository.findById(6L).get();
        this.concert = concertRepository.findById(1L).get();
        this.concertDate = concertDateRepository.findById(1L).get();
        this.selectedSeatsData = "[224]";
        this.seat = seatRepository.findById(224L).get();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // When
        executorService.execute(() -> {
            try {
                order1 = orderService.order(concert, concertDate, member, selectedSeatsData);
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        executorService.execute(() -> {
            try {
                order2 = orderService.order(concert, concertDate, member, selectedSeatsData);
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();

        // Then
        List<ConcertSeatHistory> result = concertSeatHistoryRepository.findAllBySeatAndConcertDate(seat, concertDate);
        Assertions.assertEquals(1, result.size());
    }
}
