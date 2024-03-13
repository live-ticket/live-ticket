package com.ll.ticket.domain.concert.datailPage.dto;


import com.ll.ticket.domain.concert.entity.Concert;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConcertDTO {

    private Long concertId;
    private String name;
    private Long placeId;
    private LocalDateTime releaseTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int runningTime;
    private String category;
    private String status;
    private Long seatPrice;

    public ConcertDTO(Concert concert) {

        this.concertId = concert.getConcertId();
        this.name = concert.getName();
        this.placeId = concert.getPlace().getPlaceId();
        this.releaseTime = concert.getReleaseTime();
        this.startTime = concert.getStartTime();
        this.endTime = concert.getEndTime();
        this.runningTime = concert.getRunningTime();
        this.category = concert.getCategory().name();
        this.status = concert.getStatus().name();
        this.seatPrice = concert.getSeatPrice();
    }
}
