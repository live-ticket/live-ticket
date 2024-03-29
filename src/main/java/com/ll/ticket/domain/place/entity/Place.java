package com.ll.ticket.domain.place.entity;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String placeName;

    private Double longitude;
    private Double latitude;

    private int totalPeople;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "place")
    private List<Seat> seats = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "place")
    private Concert concert;
}