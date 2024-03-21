package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.ConcertPerformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 콘서트에 해당하는 출연자를 찾는 레포지터리
 */
@Repository
public interface ConcertPerformersRepository extends JpaRepository<ConcertPerformer , Long> {

    List<ConcertPerformer> findByConcertConcertId(Long id);

}
