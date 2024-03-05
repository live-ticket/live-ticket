package com.ll.ticket.domain.member.service;

import com.ll.ticket.domain.member.dto.JoinRequest;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(JoinRequest joinRequest) {
        validateEmail(joinRequest.getEmail());
        validateConfirmPassword(joinRequest.getPassword(), joinRequest.getPasswordConfirm());
        validatePhoneNumber(joinRequest.getPhoneNumber());

        Member member = joinRequest.toEntity(joinRequest);
        memberRepository.save(member);
    }

    private void validateEmail(String email) {
        if (existsByEmail(email)) {
            throw new IllegalStateException("중복된 이메일입니다.");
        }
    }

    private void validateConfirmPassword(String password, String passwordConfirm) {
        if (!confirmPassword(password, passwordConfirm)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (existsByPhoneNumber(phoneNumber)) {
            throw new IllegalStateException("중복된 전화번호입니다.");
        }
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
    public boolean existsByPhoneNumber(String phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean confirmPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
