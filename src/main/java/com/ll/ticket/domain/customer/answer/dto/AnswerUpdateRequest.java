package com.ll.ticket.domain.customer.answer.dto;

import com.ll.ticket.domain.customer.answer.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerUpdateRequest {

    private Long customerAId;
    @NotBlank
    private String answerContent;

    private LocalDateTime modifyDate;

    public Answer toEntity() {

        return Answer.builder()
                .customerAId(this.customerAId)
                .answerContent(this.answerContent)
                .build();

    }
}
