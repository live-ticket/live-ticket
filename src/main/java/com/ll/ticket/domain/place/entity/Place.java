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

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Seat> seats = new ArrayList<>();

<<<<<<< HEAD
    @OneToOne(mappedBy = "place")
    private Concert concerts;

=======
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;
>>>>>>> f48f1d1 (fix: 엔Concert 엔티티 관계 수정)
}

