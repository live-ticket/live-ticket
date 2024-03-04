package com.ll.ticket.domain.customer.question.dto;


import com.ll.ticket.domain.customer.answer.dto.AnswerDto;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.entity.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long customerQId; //질문 아이디

    private String questionTitle; // 질문 제목

    private String questionContent; // 질문 내용

    private QuestionCategory questionCategory; //질문 유형

    private LocalDateTime createDate; //질문 생성 시간

    private LocalDateTime modifyDate; //질문 수정 시간

    private String memberName; //질문 작성자

    private List<AnswerDto> answerList; // 답변

    //Entity → DTO
    public static  QuestionDto toDto(Question question) {

        return QuestionDto.builder()
                .customerQId(question.getCustomerQId())
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .questionCategory(question.getQuestionCategory())
                .memberName(question.getMember().getName())
                .answerList(question.getAnswerList().stream()
                        .map(AnswerDto::toDto)
                        .collect(Collectors.toList()))
                .createDate(question.getCreateDate())
                .modifyDate(question.getModifyDate())
                .build();

    }

}
