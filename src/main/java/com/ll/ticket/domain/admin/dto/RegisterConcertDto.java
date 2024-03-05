package com.ll.ticket.domain.admin.dto;

<<<<<<< HEAD
import com.ll.ticket.domain.place.entity.Place;
=======
<<<<<<< HEAD
>>>>>>> 7666396 (feat : 콘서트 등록 폼 작성)
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.sql.Time;
=======
=======
import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
>>>>>>> 2c0a23a (feat : 콘서트 등록 폼 작성)
>>>>>>> 7666396 (feat : 콘서트 등록 폼 작성)
import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterConcertDto {
    @NotBlank(message = "제목을 입력하세요")
    private String name;

<<<<<<< HEAD
    @NotBlank(message = "콘서트명을 입력하세요")
    private String concertNameKr;

    @NotBlank(message = "콘서트 영문명을 입력하세요")
    private String concertNameEng;

    @NotBlank(message = "출연자를 입력하세요")
    private String artistNameKr;

    @NotBlank(message = "출연자 영문명을 입력하세요")
    private String artistNameEng;

=======
<<<<<<< HEAD
>>>>>>> 7666396 (feat : 콘서트 등록 폼 작성)
    @NotNull(message = "경도를 입력하세요")
    private Double longitude;

    @NotNull(message = "위도를 입력하세요")
    private Double latitude;

    @NotNull(message = "예매 오픈 일시를 입력하세요")
    private LocalDateTime releaseTime;

    @NotNull(message = " 공연 시작 시간을 입력하세요")
    private LocalDateTime startTime;

    @NotNull(message = "공연 종료 시간을 입력하세요")
    private LocalDateTime endTime;

    private ConcertCategory category;

    private ConcertStatus status;

    @NotNull(message = "좌석 가격을 입력하세요")
    @Min(0)
<<<<<<< HEAD
    private int seatPrice;
}
=======
    private Long seatPrice;
=======
    //@NotBlank(message = "장소를 입력하세요")
    //private Place place;

    @NotBlank(message = "예매 오픈 일시를 입력하세요")
    private LocalDateTime releaseTime;

    //@NotBlank(message = "공연 길이를 입력하세요")
    //private int runningTime;

    @NotBlank(message = " 공연 시작 시간을 입력하세요")
    private LocalDateTime startTime;

    @NotBlank(message = "공연 종료 시간을 입력하세요")
    private LocalDateTime endTime;

    @NotBlank(message = "카테코리를 입력하세요")
    private ConcertCategory category;

    @NotBlank(message = "콘서트 상태를 입력하세요")
    private ConcertStatus status;

    @NotBlank(message = "좌석 가격을 입력하세요")
    private int seatPrice;
>>>>>>> 2c0a23a (feat : 콘서트 등록 폼 작성)
}
>>>>>>> 7666396 (feat : 콘서트 등록 폼 작성)
