package com.ll.ticket.domain.customer.question.repository;

import com.ll.ticket.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMemberRepository extends JpaRepository<Member, Long> {
}
