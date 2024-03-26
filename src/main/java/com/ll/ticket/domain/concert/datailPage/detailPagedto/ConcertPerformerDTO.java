package com.ll.ticket.domain.concert.datailPage.detailPagedto;

import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.entity.Image;
import lombok.Getter;

@Getter
public class ConcertPerformerDTO {

    private final Long concertPerformerId;

    private final Long concertId;

    private final String artistNameKr;

    private final String artistNameEng;

    public ConcertPerformerDTO(ConcertPerformer concertPerformer) {

        this.concertPerformerId = concertPerformer.getConcertPerformerId();
        this.concertId = concertPerformer.getConcert().getConcertId();
        this.artistNameKr = concertPerformer.getArtistNameKr();
        this.artistNameEng = concertPerformer.getArtistNameEng();

    }
}
