package com.ll.ticket.domain.customer.answer.dto;

import com.ll.ticket.domain.customer.answer.entity.Answer;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerWriteRequest {

    private Long customerAId;

    private Member member;

    private Question question;

    @NotBlank
    private String answerContent;

    private LocalDateTime createDate;

    public Answer toEntity() {

      return  Answer.builder()
                .customerAId(this.customerAId)
                .member(this.member)
                .question(this.question)
                .answerContent(this.answerContent)
                .build();

    }

}
