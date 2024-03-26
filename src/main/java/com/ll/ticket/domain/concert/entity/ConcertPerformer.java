package com.ll.ticket.domain.concert.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConcertPerformer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertPerformerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    private String artistNameKr;
    private String artistNameEng;
}
