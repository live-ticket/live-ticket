package com.ll.ticket.domain.chat.service;

import com.ll.ticket.domain.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ChatService {

    public ChatMessage writeMessage(String writerName, String body, Long memberId) {
        // 현재 시각을 가져오는 메소드
        String currentTime = getCurrentTime();

        ChatMessage chatMessage = ChatMessage
                .builder()
                .writerName(writerName)
                .body(body)
                .memberId(memberId)
                .time(currentTime) // 현재 시각 추가
                .build();

        return chatMessage;
    }

    // 현재 시각을 가져오는 메소드
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
