package com.ll.ticket.domain.customer.answer.dto;

import com.ll.ticket.domain.customer.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private Long customerAId;

    private String answerContent; //답변 내용

    private String memberName; //작성자

    private Long customerQId; //질문

    private LocalDateTime createDate; //답변 생성 시간

    private LocalDateTime modifyDate; //답변 수정 시간


    // Entity → DTO 변환
    public AnswerResponse (Answer answer) {

      this.customerAId = answer.getCustomerAId();
      this.answerContent= answer.getAnswerContent();
      this.memberName = answer.getMember().getName();
      this.customerQId = answer.getQuestion().getCustomerQId();
      this.createDate = answer.getCreateDate();
      this.modifyDate = answer.getModifyDate();
    }

}
