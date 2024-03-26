package com.ll.ticket.domain.admin.service;

import com.ll.ticket.domain.admin.dto.RegisterConcertDto;
import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import com.ll.ticket.domain.concert.entity.Image;
import com.ll.ticket.domain.concert.repository.ConcertDateRepository;
import com.ll.ticket.domain.concert.repository.ConcertPerformerRepository;
import com.ll.ticket.domain.concert.repository.ConcertRepository;
import com.ll.ticket.domain.concert.repository.ImageRepository;
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.domain.place.repository.PlaceRepository;
import com.ll.ticket.domain.seat.entity.Seat;
import com.ll.ticket.domain.seat.repository.SeatRepository;
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final ConcertRepository concertRepository;
    private final PlaceRepository placeRepository;
    private final ConcertDateRepository concertDateRepository;
    private final ConcertPerformerRepository concertPerformerRepository;
    private final SeatRepository seatRepository;
    private final ImageRepository imageRepository;

    public void register(RegisterConcertDto registerConcertDto) throws IOException {

        String name = registerConcertDto.getName();
        String concertNameKr = registerConcertDto.getConcertNameKr();
        String concertNameEng = registerConcertDto.getConcertNameEng();
        ConcertCategory category = registerConcertDto.getCategory();
        ConcertStatus status = registerConcertDto.getStatus();
        int seatPrice = registerConcertDto.getSeatPrice();
        LocalDateTime releaseTime = registerConcertDto.getReleaseTime();
        LocalDateTime startTime = registerConcertDto.getStartTime();
        LocalDateTime endTime = registerConcertDto.getEndTime();
        int runningTime = endTime.getHour() - startTime.getHour();

        //업로드 된 이미지 처리
        MultipartFile file = registerConcertDto.getImage();
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadImages\\";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        //Concert 객체 빌드
        Concert concert = Concert.builder()
                .name(name)
                .concertNameKr(concertNameKr)
                .concertNameEng(concertNameEng)
                .releaseTime(releaseTime)
                .runningTime(runningTime)
                .category(category)
                .status(status)
                .seatPrice(seatPrice)
                .createDate(LocalDateTime.now())
                .build();

        this.concertRepository.save(concert);

        //Image 객체 빌드
        List<Image> images = new ArrayList<>();

        Image image = Image.builder()
                .concert(concert)
                .name(fileName)
                .path("/uploadImages/" + fileName)
                .build();

        images.add(image);
        imageRepository.save(image);

        //Place 객체 빌드
        Place place = Place.builder()
                .concert(concert)
                .longitude(registerConcertDto.getLongitude())
                .latitude(registerConcertDto.getLatitude())
                .totalPeople(registerConcertDto.getTotalPeople())
                .build();

        concert.setPlace(place);
        placeRepository.save(place);

        //Seat 객체 빌드
        List<Seat> seats = new ArrayList<>();

        for(int i = 1; i <= registerConcertDto.getTotalPeople(); i++){
            Seat seat = Seat.builder()
                    .place(place)
                    .seatNumber((long)i)
                    .build();

            seats.add(seat);
            seatRepository.save(seat);
        }

        //ConcertDate 객체 빌드
        List<ConcertDate> concertDates = new ArrayList<>();

        ConcertDate concertDate = ConcertDate.builder()
                .concert(concert)
                .startTime(registerConcertDto.getStartTime())
                .endTime(registerConcertDto.getEndTime())
                .build();

        concertDates.add(concertDate);
        concertDateRepository.save(concertDate);

        //ConcertPerformer 객체 빌드
        ConcertPerformer concertPerformer = ConcertPerformer.builder()
                .artistNameKr(registerConcertDto.getArtistNameKr())
                .artistNameEng(registerConcertDto.getArtistNameEng())
                .build();

        concert.setConcertPerformer(concertPerformer);
        concertPerformerRepository.save(concertPerformer);
    }

    public void modify(RegisterConcertDto registerConcertDto, Concert concert, Place place, ConcertPerformer concertPerformer, List<ConcertDate> concertDates){
        String name = registerConcertDto.getName();
        String concertNameKr = registerConcertDto.getConcertNameKr();
        String concertNameEng = registerConcertDto.getConcertNameEng();

        Place updatePlace = place.toBuilder()
                .longitude(registerConcertDto.getLongitude())
                .latitude(registerConcertDto.getLatitude())
                .totalPeople(registerConcertDto.getTotalPeople())
                .build();

        placeRepository.save(updatePlace);

        ConcertDate updateConcertDate = concertDates.get(0).toBuilder()
                .startTime(registerConcertDto.getStartTime())
                .endTime(registerConcertDto.getEndTime())
                .build();

        concertDateRepository.save(updateConcertDate);

        ConcertPerformer updateConcertPerformer = concertPerformer.toBuilder()
                .artistNameKr(registerConcertDto.getArtistNameKr())
                .artistNameEng(registerConcertDto.getArtistNameEng()).build();

        concertPerformerRepository.save(updateConcertPerformer);

        LocalDateTime releaseTime = registerConcertDto.getReleaseTime();
        LocalDateTime startTime = registerConcertDto.getStartTime();
        LocalDateTime endTime = registerConcertDto.getEndTime();
        int runningTime = endTime.getHour() - startTime.getHour();

        ConcertCategory category = registerConcertDto.getCategory();
        ConcertStatus status = registerConcertDto.getStatus();
        int seatPrice = registerConcertDto.getSeatPrice();

        Concert updateConcert = concert.toBuilder()
                .name(name)
                .concertNameKr(concertNameKr)
                .concertNameEng(concertNameEng)
                .concertPerformer(concertPerformer)
                .place(place)
                .releaseTime(releaseTime)
                .runningTime(runningTime)
                .category(category)
                .status(status)
                .seatPrice(seatPrice)
                .modifyDate(LocalDateTime.now())
                .build();

        this.concertRepository.save(updateConcert);
    }
}