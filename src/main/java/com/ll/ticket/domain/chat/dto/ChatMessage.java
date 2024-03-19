package com.ll.ticket.domain.chat.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ChatMessage {
    private String writerName;
    private String body;
    private Long memberId;
    private String time;
}
