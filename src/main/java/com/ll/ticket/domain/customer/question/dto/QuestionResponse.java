package com.ll.ticket.domain.customer.question.dto;

import com.ll.ticket.domain.customer.answer.dto.AnswerResponse;
import com.ll.ticket.domain.customer.question.entity.Question;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionResponse {

    private final Long customerQId;

    private final String questionCategory;

    private final String questionTitle;

    private final String questionContent;

    private final List<AnswerResponse> answerList; //답변

    private final Long authorId;

    private final String loginId; // 로그인 아이디 , member 엔티티 의 email

    private final String authorName;

    private final String memberRole; //로그인 사용자 권한

    private final LocalDateTime createDate;

    private final LocalDateTime modifyDate;

    private final String imagePath;

    private final String fileName;

    /* Entity -> Dto*/
        public QuestionResponse(Question question) {
            this.customerQId = question.getCustomerQId();
            this.questionTitle = question.getQuestionTitle();
            this.questionContent = question.getQuestionContent();
            this.questionCategory = question.getQuestionCategory().getValue();
            this.answerList = question.getAnswerList().stream().map(AnswerResponse::new).collect(Collectors.toList());
            this.authorId = question.getMember().getUserId();
            this.loginId = question.getMember().getEmail(); //로그인 아이디
            this.authorName=question.getMember().getName(); // 멤버 엔티티 이름
            this.memberRole=question.getMember().getRole().getValue(); //권한
            this.createDate = question.getCreateDate();
            this.modifyDate = question.getModifyDate();
            this.imagePath = question.getImagePath();
            this.fileName = question.getFileName();
        }
}
