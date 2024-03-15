package com.ll.ticket.domain.concert.entity;

import com.ll.ticket.domain.place.entity.Place;
import com.ll.ticket.global.enums.ConcertCategory;
import com.ll.ticket.global.enums.ConcertStatus;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertId;

    private String name;
    private String concertNameKr;
    private String concertNameEng;

    @OneToOne(cascade = CascadeType.REMOVE)
    private ConcertPerformer concertPerformer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ConcertDate> concertDates = new ArrayList<>();

    private LocalDateTime releaseTime;
    private int runningTime;

    @Enumerated(EnumType.STRING)
    private ConcertCategory category;

    @Enumerated(EnumType.STRING)
    private ConcertStatus status;
    private int seatPrice;

    private LocalDateTime createDate;
<<<<<<< HEAD
<<<<<<< HEAD

    @Column(nullable = true)
=======
>>>>>>> 833d680 (feat: 삭제  시 확인 추가)
=======

    @Column(nullable = true)
>>>>>>> 6eeac95 (fix: modifyDate 옵션 수정)
    private LocalDateTime modifyDate;

    public void setStatus(ConcertStatus status) {
        this.status = status;
    }


}