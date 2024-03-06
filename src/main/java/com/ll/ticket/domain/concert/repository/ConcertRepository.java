package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
=======
>>>>>>> 8b4294a (feat : 콘서트 등록 구현)

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Page<Concert> findAll(Pageable pageable);

    @Query("SELECT c FROM Concert c join fetch c.place p " +
            "WHERE LOWER(c.concertNameKr) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.concertNameEng) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(p.placeName) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "OR LOWER(c.category) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "AND c.status = 'ENABLE'")
    List<Concert> findByConcertKeyword(@Param("keyword") String keyword);
}