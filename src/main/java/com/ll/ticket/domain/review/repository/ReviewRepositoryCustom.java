package com.ll.ticket.domain.review.repository;

import com.ll.ticket.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<Review> findByConcertConcertIdOrderByDateDesc(Long concertId , Pageable pageable);
}
