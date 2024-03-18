package com.ll.ticket.domain.review.dto;

import com.ll.ticket.domain.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {


    private final Long reviewId;

    private final String authorName;

    private final Long concertId;

    private final String content;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifyDate;

    public ReviewResponse(Review review) {

        this.reviewId = review.getReviewId();
        this.authorName = review.getMember().getName();
        this.concertId = review.getConcert().getConcertId();
        this.content = review.getContent();
        this.createdDate = review.getCreateDate();
        this.modifyDate = review.getModifyDate();
    }
}
