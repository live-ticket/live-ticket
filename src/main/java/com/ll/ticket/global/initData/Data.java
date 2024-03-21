package com.ll.ticket.global.initData;

import com.ll.ticket.domain.concert.service.QueueService;
import com.ll.ticket.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RequiredArgsConstructor
public class Data {
    private final QueueService queueService;
    private final MemberService memberService;
    private final RedisTemplate<String, String> redisTemplate;

    @Bean
    public ApplicationRunner initAll() {
        return args -> {
            long now = System.currentTimeMillis();
            for (int i = 1000; i < 1010; i++) {
                redisTemplate.opsForZSet().add("대기열", String.valueOf(i), now);
            }

//            memberService.findByUserId(6L).ifPresent(member -> queueService.addQueue("대기열", member));
//            memberService.findByUserId(47L).ifPresent(member -> queueService.addQueue("대기열", member));
        };
    }
}
