package com.ll.ticket.domain.review.repository;

import com.ll.ticket.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findByConcertConcertIdOrderByDateDesc(Long concertId);
}
