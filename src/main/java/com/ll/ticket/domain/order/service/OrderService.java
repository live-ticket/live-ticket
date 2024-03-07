package com.ll.ticket.domain.order.service;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.order.dto.OrderPayInfoDto;
import com.ll.ticket.domain.order.entity.Order;
import com.ll.ticket.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order order(Concert concert, String concertDateId, Member member) {
        Order order = Order.builder()
                .member(member)
                .orderPrice(concert.getSeatPrice())
                .build();

        order.addTicket(concert);

        orderRepository.save(order);

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
        return order.getMember().equals(member);
    }

    @Transactional
    public void saveOrderPayInfo(Order order, OrderPayInfoDto orderPayInfoDto) {
        order.setName(orderPayInfoDto.getCustomerName());
        order.setPhoneNumber(orderPayInfoDto.getCustomerMobilePhoneNumber());
        order.setOrderPrice(order.getOrderPrice());
        order.setAddress1(orderPayInfoDto.getAddress1());
        order.setAddress2(orderPayInfoDto.getAddress2());
        order.setZipcode(orderPayInfoDto.getZipcode());
    }
}
