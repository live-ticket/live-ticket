package com.ll.ticket.domain.admin.controller;

import com.ll.ticket.domain.admin.dto.RegisterConcertDto;
import com.ll.ticket.domain.admin.service.AdminService;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.service.ConcertDateService;
import com.ll.ticket.domain.concert.service.ConcertPerformerService;
import com.ll.ticket.domain.concert.service.ConcertService;
<<<<<<< HEAD
<<<<<<< HEAD
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.place.service.PlaceService;
=======
>>>>>>> 8b4294a (feat : 콘서트 등록 구현)
=======
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.place.service.PlaceService;
>>>>>>> fb33f1c (ãfix: ìãx: 엔티티 관련 서비스 객체 추가)
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ConcertService concertService;
    private final ConcertDateService concertDateService;
    private final ConcertPerformerService concertPerformerService;
    private final PlaceService placeService;

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

<<<<<<< HEAD
<<<<<<< HEAD
        return "redirect:/admin/concertList";
=======
        return "redirect:/";
>>>>>>> 8b4294a (feat : 콘서트 등록 구현)
=======
        return "redirect:/admin/concertList";
>>>>>>> 6eeac95 (fix: modifyDate 옵션 수정)
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
<<<<<<< HEAD
        Place place = this.adminService.findPlace(concert);
        ConcertPerformer concertPerformer = this.adminService.findConcertPerformer(concert);
        List<ConcertDate> concertDates = this.adminService.findConcertDates(concert);

        model.addAttribute("concert", concert);
        model.addAttribute("place", place);
        model.addAttribute("concertPerformer", concertPerformer);
        model.addAttribute("concertDates", concertDates);
=======
        //ConcertDate concertDate = this.

        model.addAttribute("concert", concert);
        //model.addAttribute("")
>>>>>>> fb33f1c (ãfix: ìãx: 엔티티 관련 서비스 객체 추가)

        return "domain/admin/concert_detail";
    }


    //TO DO 콘서트 글 수정

    //콘서트 글 삭제
    @GetMapping(value = "/deleteConcert/{id}")
    public String deleteConcert(@PathVariable("id") Long id){
        Concert concert = this.concertService.findById(id);

        this.concertService.delete(concert);

        return "redirect:/admin/concertList";
    }
}