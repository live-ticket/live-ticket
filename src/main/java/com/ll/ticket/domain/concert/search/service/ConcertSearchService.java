package com.ll.ticket.domain.concert.search.service;

import com.ll.ticket.domain.concert.dto.ConcertResponse;
import com.ll.ticket.domain.concert.dto.ConcertSearchResponse;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ConcertSearchService {
    private final ConcertRepository concertRepository;

    public ConcertSearchResponse findByKeyword(String keyword) {
        //Concert 검색 상품을 담는 배열
        List<ConcertResponse> concerts = new ArrayList<>();
        List<Concert> findByConcertKeyword = concertRepository.findByConcertKeyword(keyword);

        for(Concert concert : findByConcertKeyword){
            concerts.add(ConcertResponse.of(concert));
        }

        return ConcertSearchResponse.of(keyword, concerts);
    }
}
