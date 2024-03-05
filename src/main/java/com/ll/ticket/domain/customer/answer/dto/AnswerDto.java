package com.ll.ticket.domain.customer.answer.dto;

import com.ll.ticket.domain.customer.answer.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private Long customerAId;

    @NotBlank
    private String answerContent; //답변 내용

    private String memberName; //작성자

    private Long customerQId; //질문

    private LocalDateTime createDate; //답변 생성 시간

    private LocalDateTime modifyDate; //답변 수정 시간


    // Entity → DTO 변환
    public static AnswerDto toDto(final Answer answer) {

        return AnswerDto.builder()
                .customerAId(answer.getCustomerAId())
                .answerContent(answer.getAnswerContent())
                .memberName(answer.getMember().getName())
                .customerQId(answer.getQuestion().getCustomerQId())
                .createDate(answer.getCreateDate())
                .modifyDate(answer.getModifyDate())
                .build();

    }



}
