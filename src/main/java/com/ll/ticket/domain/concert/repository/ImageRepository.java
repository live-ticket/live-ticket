package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
