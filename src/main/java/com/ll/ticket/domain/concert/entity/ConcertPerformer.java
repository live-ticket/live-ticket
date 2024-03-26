package com.ll.ticket.domain.concert.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConcertPerformer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertPerformerId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "concertPerformer")
    private Concert concert;

    private String artistNameKr;
    private String artistNameEng;
}