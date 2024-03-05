package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> 7666396 (feat : 콘서트 등록 폼 작성)

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Page<Concert> findAll(Pageable pageable);
}