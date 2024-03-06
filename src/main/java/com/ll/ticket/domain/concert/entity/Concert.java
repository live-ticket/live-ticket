package com.ll.ticket.domain.concert.entity;

import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertId;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private LocalDateTime releaseTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int runningTime;

    @Enumerated(EnumType.STRING)
    private ConcertCategory category;

    @Enumerated(EnumType.STRING)
    private ConcertStatus status;
    private int seatPrice;

}
