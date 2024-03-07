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

    @NotNull(message = "문의 유형을 선택해주세요.")
    private QuestionCategory questionCategory;

    @NotBlank(message = "제목을 입력해주세요")
    private String questionTitle;

    @NotBlank(message = "내용을 입력해주세요")
    private String questionContent;

    private Member member;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private String imagePath;

    private String fileName;


    public Question toEntity() {

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
