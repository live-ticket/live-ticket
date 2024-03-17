package com.ll.ticket.domain.concert.datailPage.dto;


import com.ll.ticket.domain.concert.entity.Concert;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConcertDTO {

    private final Long concertId;

    private final Long placeId;

    private final String concertNameKr;

    private final String concertNameEng;

    private final String artistNameKr;

    private final String artistNameEng;

    private final LocalDateTime releaseTime;

//    private final LocalDateTime startTime;  //기존
//
//    private final LocalDateTime endTime; // 기존

    private final int runningTime;

    private final String category;

    private final String status;

    private final int seatPrice;
    public ConcertDTO(Concert concert) {

        this.concertId = concert.getConcertId(); //콘서트 ID
        this.placeId = concert.getPlace().getPlaceId(); //장소
        this.concertNameKr = concert.getConcertNameKr(); //콘서트 한글명
        this.concertNameEng = concert.getConcertNameEng();// 콘서트 영문명
        this.artistNameKr = concert.getArtistNameKr(); // 배우 한글 명
        this.artistNameEng = concert.getArtistNameEng(); // 배우 영문명
        this.releaseTime = concert.getReleaseTime(); // 공연 오픈 시간
//        this.startTime = concert.getStartTime(); // 공연 시작시간
//        this.endTime = concert.getEndTime(); // 공연 종료 시간
        this.runningTime = concert.getRunningTime(); //공연 중 시간
        this.category = concert.getCategory().name(); // 콘서트 이름
        this.status = concert.getStatus().name(); //콘서트 상태
        this.seatPrice = concert.getSeatPrice(); //콘서트 가격

    }
//    public Duration calculateViewingTime() {
//        return Duration.between(startTime, endTime); //관람 시간 계산
//    }

}
