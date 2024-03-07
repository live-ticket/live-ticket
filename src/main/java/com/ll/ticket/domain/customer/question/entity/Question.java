package com.ll.ticket.domain.customer.question.entity;


import com.ll.ticket.domain.customer.answer.entity.Answer;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
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
    private QuestionCategory  questionCategory;

    @Column(length = 100, nullable = false)
    private String questionTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionContent;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList; //댓글

    @Column(length = 100)
    private String imagePath; //파일 경로

    private String fileName; //파일 이름

//    public void changeQuestion(String questionTitle , String questionContent, QuestionCategory  questionCategory , String imagePath , String fileName ) {
//
//        this.questionTitle = questionTitle;
//        this.questionContent = questionContent;
//        this.questionCategory = questionCategory;
//        this.imagePath = imagePath;
//        this.fileName = fileName;
//    }
}
