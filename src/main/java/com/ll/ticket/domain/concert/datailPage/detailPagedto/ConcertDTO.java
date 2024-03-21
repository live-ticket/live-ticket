package com.ll.ticket.domain.concert.datailPage.detailPagedto;


import com.ll.ticket.domain.concert.entity.Concert;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConcertDTO {

    private final Long concertId;

    private final Long placeId;

    private final String concertNameKr;

    private final String concertNameEng;

    private final LocalDateTime releaseTime;

    private final int runningTime;

    private final String category;

    private final String status;

    private final int seatPrice;

    public ConcertDTO(Concert concert) {

        this.concertId = concert.getConcertId(); //콘서트 ID
        this.placeId = concert.getPlace().getPlaceId(); //장소
        this.concertNameKr = concert.getConcertNameKr(); //콘서트 한글명
        this.concertNameEng = concert.getConcertNameEng();// 콘서트 영문명
        this.releaseTime = concert.getReleaseTime(); // 공연 오픈 시간
        this.runningTime = concert.getRunningTime(); //공연 중 시간
        this.category = concert.getCategory().name(); // 콘서트 이름
        this.status = concert.getStatus().name(); //콘서트 상태
        this.seatPrice = concert.getSeatPrice(); //콘서트 가격

    }


}
