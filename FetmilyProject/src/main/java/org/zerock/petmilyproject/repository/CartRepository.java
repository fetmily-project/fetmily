package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Item;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.member.memberId = :memberId")
    List<Cart> findAllByMemberId(Long memberId);

    @Query("select i from Item i, Cart c where i.itemId=c.item.itemId and c.member.memberId=:memberId")
    List<Item> findAllByMemberId1(Long memberId);

    @Query("select c from Cart c where c.member.memberId = :memberId and c.status = 1")
    List<Cart> findOrderByMemberId(Long memberId);

    @Query("select i from Cart c, Item i where c.member.memberId = :memberId and i.itemId = c.item.itemId and c.status = 1")
    List<Item> findOrderItemByMemberId(Long memberId);
}
