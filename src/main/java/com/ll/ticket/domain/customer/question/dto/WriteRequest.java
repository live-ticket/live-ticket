package com.ll.ticket.domain.customer.question.dto;

import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.entity.QuestionCategory;
import com.ll.ticket.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteRequest {

    private Long customerQId;

    @NotNull
    private QuestionCategory questionCategory;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionContent;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private String imagePath;

    private String fileName;


    public Question toEntity(Member member) {

        return Question.builder()
                .customerQId(customerQId)
                .questionTitle(questionTitle)
                .questionContent(questionContent)
                .questionCategory(questionCategory)
                .fileName(fileName)
                .imagePath(imagePath)
                .member(member)
                .build();

    }

}
