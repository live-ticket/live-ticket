package com.ll.ticket.domain.concert.repository;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertTimeRepository extends JpaRepository<ConcertDate , Long> {

    /**
     * 공연 날짜에 해당 하는 콘서트를 찾는다.
     * */
    List<ConcertDate> findByConcert(Concert concert);

}
