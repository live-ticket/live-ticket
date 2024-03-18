package com.ll.ticket.domain.concert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ConcertDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertDateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    private LocalDate concertDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
