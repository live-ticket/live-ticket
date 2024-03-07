package com.ll.ticket.domain.member.service;

import com.ll.ticket.domain.member.dto.JoinRequest;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import com.ll.ticket.global.enums.LoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinRequest joinRequest) {
        validateEmail(joinRequest.getEmail());
        validateConfirmPassword(joinRequest.getPassword(), joinRequest.getPasswordConfirm());
        validatePhoneNumber(joinRequest.getPhoneNumber());

        Member member = joinRequest.toEntity(joinRequest);
        member.changePassword(passwordEncoder.encode(joinRequest.getPassword()));
        memberRepository.save(member);
    }

    public void validateEmail(String email) {
        Optional<Member> _findMember = findByEmail(email);
        if (_findMember.isPresent()) {
            Member findMember = _findMember.get();
            if (findMember.getLoginType().equals(LoginType.KAKAO)) {
                throw new IllegalStateException("카카오 로그인 계정입니다.");
            }
            if (findMember.getLoginType().equals(LoginType.APP)) {
                throw new IllegalStateException("이미 회원가입한 이메일입니다.");
            }
        }
    }

    public void validateConfirmPassword(String password, String passwordConfirm) {
        if (!confirmPassword(password, passwordConfirm)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (existsByPhoneNumber(phoneNumber)) {
            throw new IllegalStateException("중복된 전화번호입니다.");
        }
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    public boolean existsByPhoneNumber(String phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean confirmPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public boolean existsByEmailAndLoginType(String kakaoEmail, LoginType loginType) {
        return memberRepository.existsByEmailAndLoginType(kakaoEmail, loginType);
    }

    public Member saveMember(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }
}
