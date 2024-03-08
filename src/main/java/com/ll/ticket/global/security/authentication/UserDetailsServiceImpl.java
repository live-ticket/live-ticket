package com.ll.ticket.global.security.authentication;

import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import com.ll.ticket.global.security.config.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다"));
//        return UserPrincipal.create(member);
        return new SecurityUser(
                member.getUserId(),
                member.getEmail(),
                member.getPassword(),
                member.getAuthorities());
    }
}
