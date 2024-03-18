package com.ll.ticket.domain.concert.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ConcertPerformer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertPerformerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    private String artistNameKr;
    private String artistNameEng;
}
