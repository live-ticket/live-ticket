package com.ll.ticket.domain.member.repository;

import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.global.enums.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByEmailAndLoginType(String email, LoginType loginType);
    boolean existsByPhoneNumber(String phoneNumber);
}
