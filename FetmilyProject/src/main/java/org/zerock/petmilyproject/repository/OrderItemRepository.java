package org.zerock.petmilyproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("select o from OrderItem o where o.member.memberId = :memberId")
    List<OrderItem> findAllByMemberId(Long memberId);
}
