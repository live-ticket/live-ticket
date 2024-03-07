package com.ll.ticket.domain.order.entity;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.ticket.entity.Ticket;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;
    private Long orderPrice;
    private String name;
    private String address1; // 주소
    private String address2; // 상세주소
    private String zipcode;
    private String phoneNumber;
    private String status;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Ticket> ticket = new ArrayList<>();

    public void setPaymentDone() {
        this.orderDate = LocalDateTime.now();
        this.status = "PAID";
    }

    public boolean isPayable() {
        if (orderDate != null) {
            return false;
        }

        return true;
    }

    public void addTicket(Concert concert) {
        Ticket ticket = Ticket.builder()
                .order(this)
                .concert(concert)
                .build();

        this.ticket.add(ticket);
    }

    public String getCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return getCreateDate().format(formatter) + UUID.randomUUID().toString() + "_" + getOrderId();
    }

    public String getOrderName() {
        String name = ticket.get(0).getConcert().getName();

//        if (orderDetails.size() > 1) {
//            name += " 외 %d건".formatted(orderDetails.size() - 1);
//        }

        return name;
    }

    public boolean isShippingInfo() {
        if (name != null && phoneNumber != null && address1 != null && address2 != null && zipcode != null ) return true;

        return false;
    }
}
