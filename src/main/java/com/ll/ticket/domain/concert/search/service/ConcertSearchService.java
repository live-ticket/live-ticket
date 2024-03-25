package com.ll.ticket.domain.concert.search.service;

import com.ll.ticket.domain.concert.dto.ConcertResponse;
import com.ll.ticket.domain.concert.dto.ConcertSearchResponse;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ConcertSearchService {
    private final ConcertRepository concertRepository;
    private final ConcertDateRepository concertDateRepository;

    public ConcertSearchResponse findByKeyword(String keyword) {
        //Concert 검색 상품을 담는 배열
        List<ConcertResponse> concerts = new ArrayList<>();
        List<Concert> findByConcertKeyword = concertRepository.findByConcertKeyword(keyword);

        for (Concert concert : findByConcertKeyword) {
            // Concert 엔티티의 concertDates를 로딩
            List<ConcertDate> concertDates = concertDateRepository.findAllByConcert(concert);;
            // ConcertResponse 생성
            ConcertResponse concertResponse = ConcertResponse.of(concert);
//            // ConcertResponse에 concertDates 설정
            concertResponse.setConcertDates(concertDates);
            // ConcertResponse를 리스트에 추가
            concerts.add(concertResponse);
        }


        return ConcertSearchResponse.of(keyword, concerts);
    }
}
