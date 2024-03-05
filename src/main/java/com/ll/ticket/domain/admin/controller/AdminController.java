package com.ll.ticket.domain.admin.controller;

import com.ll.ticket.domain.admin.dto.RegisterConcertDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    //콘서트 등록
    @GetMapping("/registerConcert")
    public String registerConcert() {
        return "domain/concert/registerConcert";
    }


    //콘서트 등록
    @PostMapping("/registerConcert")
    public String registerConcert(@Valid RegisterConcertDto registerConcertDto, BindingResult bindingResult) {
        //TO DO 글 저장
        return "domain/concert/registerConcert";
    }
}
