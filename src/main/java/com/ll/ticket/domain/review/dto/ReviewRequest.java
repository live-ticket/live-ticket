package com.ll.ticket.domain.review.dto;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.review.entity.Review;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewRequest {

    private Long reviewId;

    private Member member;

    @NotBlank(message = "내용을 입력 해주세요.")
    private String content;

    private Concert concert;

    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;

    public Review toEntity() {

        return Review.builder()
                .reviewId(this.reviewId)
                .member(this.member)
                .concert(this.concert)
                .content(this.content)
                .build();
    }
}
