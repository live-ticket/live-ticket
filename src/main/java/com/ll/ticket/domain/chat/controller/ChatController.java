package com.ll.ticket.domain.chat.controller;

import com.ll.ticket.domain.chat.dto.ChatMessage;
import com.ll.ticket.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final RabbitTemplate template;

    public record CreateMessageReqBody(String writerName, String body, Long memberId) {
    }

    @MessageMapping("/chat/messages/create")
    public void createMessage(CreateMessageReqBody createMessageReqBody) {

        ChatMessage chatMessage = chatService.writeMessage(createMessageReqBody.writerName(), createMessageReqBody.body(), createMessageReqBody.memberId());
        System.out.println("createMessageReqBody.memberId = " + createMessageReqBody.memberId);

        template.convertAndSend("amq.topic", "chat" + "MessageCreated", chatMessage);
    }

}
