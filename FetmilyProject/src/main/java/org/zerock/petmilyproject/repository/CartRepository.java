package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Member;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.member.memberId = :memberId")
    List<Cart> findAllByMemberId(Long memberId);
}
