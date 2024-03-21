package com.ll.ticket.domain.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueueRequestDto {
    private Long concertDateId;
    private String memberId;
}
