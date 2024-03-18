package com.ll.ticket.domain.place.entity;

import com.ll.ticket.domain.concert.entity.Concert;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String placeName;

    private Double longitude;
    private Double latitude;

    private int totalPeople;

    @OneToOne(mappedBy = "place")
    private Concert concerts;
}
