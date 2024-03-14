package com.ll.ticket.domain.admin.controller;

import com.ll.ticket.domain.admin.dto.RegisterConcertDto;
import com.ll.ticket.domain.admin.service.AdminService;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.service.ConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ConcertService concertService;

    //관리자 페이지 메인화면
    @GetMapping(value = "/main")
    public String main(){
        return "domain/admin/admin_main";
    }

    //콘서트 등록
    @GetMapping(value = "/registerConcert")
    public String registerConcert(RegisterConcertDto registerConcertDto) {
        return "domain/admin/register_concert";
    }


    //콘서트 등록
    @PostMapping(value = "/registerConcert")
    public String registerConcert(@Valid RegisterConcertDto registerConcertDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/admin/register_concert";
        }

        adminService.register(registerConcertDto);

        return "redirect:/";
    }

    //콘서트 목록
    @GetMapping(value = "/concertList")
    public String concertList(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Concert> concertList = this.concertService.getConcertList(page);
        model.addAttribute("concertList", concertList);

        return "domain/admin/concert_list";
    }

    //관리자용 콘서트 글 상세 페이지
    @GetMapping(value = "/concertDetail/{id}")
    public String concertDetail(Model model, @PathVariable("id") Long id){
        Concert concert = this.concertService.findById(id);
        model.addAttribute("concert", concert);

        return "domain/admin/concert_detail";
    }

    //콘서트 글 수정
    //TO DO

    //콘서트 글 삭제
    @GetMapping(value = "/deleteConcert/{id}")
    public String deleteConcert(@PathVariable("id") Long id){
        Concert concert = this.concertService.findById(id);
        this.concertService.delete(concert);

        return "redirect:/admin/concertList";
    }
}