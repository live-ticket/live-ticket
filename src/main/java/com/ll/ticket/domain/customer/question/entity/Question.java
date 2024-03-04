package com.ll.ticket.domain.customer.question.entity;


import com.ll.ticket.domain.customer.answer.entity.Answer;
import com.ll.ticket.domain.customer.question.dto.QuestionDto;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerQId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(value = EnumType.STRING)
    private QuestionCategory questionCategory;

    private String questionTitle;

    private String questionContent;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList; //댓글

    //Entity → DTO
    public static Question toEntity(QuestionDto questionDto ) {

        return  Question.builder()
                .customerQId(questionDto.getCustomerQId())
                .questionTitle(questionDto.getQuestionTitle())
                .questionContent(questionDto.getQuestionContent())
                .questionCategory(questionDto.getQuestionCategory())
                .answerList(questionDto.getAnswerList().stream()
                        .map(Answer::toEntity) // AnswerDto를 Answer 엔티티로 변환
                        .collect(Collectors.toList()))
                .build();


    }
}
