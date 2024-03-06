package com.ll.ticket.domain.customer.question.dto;

import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.entity.QuestionCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Long customerQId;

    private QuestionCategory questionCategory;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionContent;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;


    public Question toEntity() {


        return Question.builder()
                .customerQId(customerQId)
                .questionTitle(questionTitle)
                .questionContent(questionContent)
                .questionCategory(questionCategory)
                .build();

    }

}