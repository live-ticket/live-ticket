package com.ll.ticket.domain.customer.question.dto;

import com.ll.ticket.domain.customer.question.entity.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResponse {

    private final Long customerQId;

    private final String questionCategory;

    private final String questionTitle;

    private final String questionContent;

    private final Long authorId;

    private final String author;

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
            this.authorId = question.getMember().getUserId();
            this.author = question.getMember().getName();
            this.createDate = question.getCreateDate();
            this.modifyDate = question.getModifyDate();
            this.imagePath = question.getImagePath();
            this.fileName = question.getFileName();
        }
}
