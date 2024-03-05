package com.ll.ticket.domain.customer.answer.entity;


import com.ll.ticket.domain.customer.answer.dto.AnswerDto;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerAId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    //DTO  → Entity 변환
    public static Answer toEntity(final AnswerDto answerDto) {

        return Answer.builder()
                .customerAId(answerDto.getCustomerAId())
                .answerContent(answerDto.getAnswerContent())
                .build();

    }


}
