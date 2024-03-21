package com.ll.ticket.domain.concert.search.controller;

import com.ll.ticket.domain.concert.dto.ConcertResponse;
import com.ll.ticket.domain.concert.dto.ConcertSearchResponse;
import com.ll.ticket.domain.concert.search.service.ConcertSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ConcertSearchController {
    private final ConcertSearchService concertSearchService;
    //localhost:8080/concert/search?keyword=박재정
    //localhost:8080/concert/search/keyword?keyword=박재정
        @GetMapping("/concert/search")
        public String concertSearch(@RequestParam(value = "keyword") String keyword,
                                Model model) {
            ConcertSearchResponse data = concertSearchService.findByKeyword(keyword);

            List<ConcertResponse> concertList = data.getConcert();
            for (ConcertResponse concertResponse : concertList) {
                log.info("concertId: {}", concertResponse.getId());
            }

            model.addAttribute("keyword", keyword);
            model.addAttribute("data", data);
            return "domain/concert/concertsearch/list";
    }

}
