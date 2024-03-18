package com.ll.ticket.domain.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertSearchResponse {
    private String keyword;

    @Builder.Default
    List<ConcertResponse> concert = new ArrayList<>();

    public static ConcertSearchResponse of(String keyword, List<ConcertResponse> concert){
        return ConcertSearchResponse.builder()
                .keyword(keyword)
                .concert(concert)
                .build();
    }
}
