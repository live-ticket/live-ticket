package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 8b4294a (feat : 콘서트 등록 구현)

>>>>>>> 5cd886a (feat : 콘서트 등록 구현)
=======
>>>>>>> fd2939e (feat: 관리자용 글 상세 페이지 카카오 지도 추가)
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Page<Concert> findAll(Pageable pageable);

    @Query("SELECT distinct c FROM Concert c join fetch c.place p left join fetch c.concertPerformer cp left join fetch c.images " +
            "WHERE LOWER(c.concertNameKr) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(cp.artistNameEng) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(cp.artistNameKr) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.concertNameEng) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(p.placeName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.category) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "AND c.status = 'ENABLE'")
    List<Concert> findByConcertKeyword(@Param("keyword") String keyword);
}