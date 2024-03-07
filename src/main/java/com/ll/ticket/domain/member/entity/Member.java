package com.ll.ticket.domain.member.entity;

import com.ll.ticket.global.enums.Gender;
import com.ll.ticket.global.enums.LoginType;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String providerId;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.role != null && this.role.equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("MEMBER"));
        }
        return authorities;
    }

    public void changePassword(String password) {
        this.password = password;
    }

}
