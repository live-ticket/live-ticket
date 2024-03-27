package com.ll.ticket.domain.concert.datailPage.detailPagedto;

import com.ll.ticket.domain.concert.entity.ConcertDate;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ConcertDateDTO {


    private final Long concertDateId;

    private final Long concertId;

    private final LocalDate concertDate;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    public ConcertDateDTO (ConcertDate concertDate)  {

        this.concertDateId = concertDate.getConcertDateId();
        this.concertId = concertDate.getConcert().getConcertId();
        this.concertDate = concertDate.getConcertDate();
        this.startTime = concertDate.getStartTime();
        this.endTime = concertDate.getEndTime();
    }
}
