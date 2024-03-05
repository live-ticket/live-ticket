package com.ll.ticket.domain.customer.question.dto;

import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.entity.QuestionCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDto {

    private Long customerQId;

    private QuestionCategory questionCategory;

    private String questionTitle;

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

    @Builder
    public QuestionDto (Long customerQId , String questionTitle , String questionContent , QuestionCategory questionCategory,
                  LocalDateTime createDate ,   LocalDateTime modifyDate ) {

        this.customerQId = customerQId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionCategory = questionCategory;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
