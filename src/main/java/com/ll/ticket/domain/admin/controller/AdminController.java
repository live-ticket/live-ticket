package com.ll.ticket.domain.admin.controller;

import com.ll.ticket.domain.admin.dto.RegisterConcertDto;
<<<<<<< HEAD
import com.ll.ticket.domain.admin.service.AdminService;
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
=======
>>>>>>> 2c0a23a (feat : 콘서트 등록 폼 작성)
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

<<<<<<< HEAD
    private final AdminService adminService;

    //콘서트 등록
    @GetMapping("/registerConcert")
    public String registerConcert(RegisterConcertDto registerConcertDto) {
=======
    //콘서트 등록
    @GetMapping("/registerConcert")
    public String registerConcert() {
>>>>>>> 2c0a23a (feat : 콘서트 등록 폼 작성)
        return "domain/concert/registerConcert";
    }


    //콘서트 등록
    @PostMapping("/registerConcert")
    public String registerConcert(@Valid RegisterConcertDto registerConcertDto, BindingResult bindingResult) {
<<<<<<< HEAD
        if (bindingResult.hasErrors()) {
            return "domain/concert/registerConcert";
        }

        adminService.register(registerConcertDto);

        return "redirect:/";
=======
        //TO DO 글 저장
        return "domain/concert/registerConcert";
>>>>>>> 2c0a23a (feat : 콘서트 등록 폼 작성)
    }
}
