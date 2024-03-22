package com.ll.ticket.domain.concert.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class QueueService {
    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    public void addQueue(String eventName, String memberId) {
//        String memberId = member.getUserId().toString();
        long now = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(eventName, memberId, now);
        log.info("대기열 추가: {}", memberId, now);
    }

    public void processQueue(String eventName) {
        Set<String> queue = redisTemplate.opsForZSet().range(eventName, 0, 0);

        for (String memberId : queue) {
            redisTemplate.opsForZSet().remove(eventName, memberId);
            log.info("대기열 제거: {}", memberId);
            messagingTemplate.convertAndSend("/queue/processQueue/" + memberId, "대기열 통과");
        }
    }

    public void listQueue(String eventName) {
        Set<String> queue = redisTemplate.opsForZSet().range(eventName, 0, -1);

        for (String memberId : queue) {
            Long rank = redisTemplate.opsForZSet().rank(eventName, memberId);
            log.info("대기열 목록: {}, {} 남음", memberId, rank);
//            HashMap<String, Object> payload = new HashMap<>();
//            payload.put("rank", rank);

            messagingTemplate.convertAndSend("/queue/listQueue/" + memberId, rank);
        }
    }
}