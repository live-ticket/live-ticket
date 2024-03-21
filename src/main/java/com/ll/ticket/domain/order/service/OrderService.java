package com.ll.ticket.domain.order.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertSeatHistory;
import com.ll.ticket.domain.concert.repository.ConcertSeatHistoryRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.order.dto.OrderPayInfoDto;
import com.ll.ticket.domain.order.entity.Order;
import com.ll.ticket.domain.order.repository.OrderRepository;
import com.ll.ticket.domain.seat.entity.Seat;
import com.ll.ticket.domain.seat.repository.SeatRepository;
import com.ll.ticket.domain.ticket.entity.Ticket;
import com.ll.ticket.domain.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final ConcertSeatHistoryRepository concertSeatHistoryRepository;

    @Transactional
    public Order order(Concert concert, ConcertDate concertDate, Member member, String selectedSeatsData) {
        String selectedSeatsString = selectedSeatsData.substring(1, selectedSeatsData.length() - 1);
        String[] seatIdsArray = selectedSeatsString.split(",");
        List<Long> seatIds = Arrays.stream(seatIdsArray)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<ConcertSeatHistory> seatHistoryList = concertSeatHistoryRepository.findAllAndLockByConcertDate(concertDate);

        for (ConcertSeatHistory seatHistory : seatHistoryList) {
            if (seatIds.contains(seatHistory.getSeat().getSeatId())) {
                throw new IllegalArgumentException("이미 예약된 좌석입니다.");
            }
        }

        Order order = Order.builder()
                .member(member)
                .orderPrice(Long.valueOf(concert.getSeatPrice() * seatIds.size()))
                .build();

        orderRepository.save(order);

        for (Long seatId : seatIds) {
            Ticket ticket = Ticket.builder()
                    .order(order)
                    .concert(concert)
                    .build();

            ticketRepository.save(ticket);

            Seat seat = seatRepository.findAndLockBySeatId(seatId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌석입니다."));

            ConcertSeatHistory concertSeatHistory = ConcertSeatHistory.builder()
                    .concertDate(concertDate)
                    .seat(seat)
                    .ticket(ticket)
                    .build();
            concertSeatHistoryRepository.save(concertSeatHistory);
        }

        return order;
    }

    public boolean canPay(Order order, long pgPayPrice) {
        if ( !order.isPayable() ) return false;

        return order.getOrderPrice() <= pgPayPrice;
    }

    public void checkCanPay(Order order, long pgPayPrice) {
        if (!canPay(order, pgPayPrice))
            throw new IllegalArgumentException("PG결제금액 부족하여 결제할 수 없습니다.");
    }

    public void checkCanPay(String orderCode, long pgPayPrice) {
        Order order = findByCode(orderCode).orElse(null);

        if (order == null)
            throw new IllegalArgumentException("존재하지 않는 주문입니다.");

        checkCanPay(order, pgPayPrice);
    }

    public Optional<Order> findByCode(String code) {
        long id = Long.parseLong(code.split("_", 2)[1]);

        return findById(id);
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void payByTossPayments(Order order, long pgPayPrice) {
        payDone(order);
    }

    public void payDone(Order order) {
        order.setPaymentDone();
    }

    public boolean checkOrderAccess(Member member, Order order) {
        return order.getMember().getUserId().equals(member.getUserId());
    }

    @Transactional
    public void saveOrderPayInfo(Order order, OrderPayInfoDto orderPayInfoDto) {
        order.savePayInfo(orderPayInfoDto);

        orderRepository.save(order);
    }
}
