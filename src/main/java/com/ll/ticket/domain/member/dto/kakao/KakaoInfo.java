package com.ll.ticket.domain.member.dto.kakao;

import com.ll.ticket.global.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class KakaoInfo {

    private String id;
    private String name;
    private String email;
    private Gender gender;
    private LocalDate birthday;
    private String phoneNumber;


    public KakaoInfo(String id, String name, String email, String gender, String birthYear, String birthMonthAndDay, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = null;
        this.birthday = null;
        this.phoneNumber = null;
//        this.gender = gender.equals("male") ? Gender.MALE : Gender.FEMALE;
//        this.birthday = LocalDate.of(Integer.parseInt(birthYear),
//                Integer.parseInt(birthMonthAndDay.substring(0, 2)),
//                Integer.parseInt(birthMonthAndDay.substring(2)));
//        this.phoneNumber = "0" + phoneNumber.split(" ")[1];
    }
}
