package com.ll.ticket.domain.review.repository;

import com.ll.ticket.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review , Long> , ReviewRepositoryCustom{


}