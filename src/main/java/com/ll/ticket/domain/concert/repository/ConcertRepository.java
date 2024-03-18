package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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