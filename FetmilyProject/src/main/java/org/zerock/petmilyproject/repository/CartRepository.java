package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Cart;

import java.util.List;
import org.zerock.petmilyproject.domain.Item;

public interface CartRepository extends JpaRepository<Cart, Long> {
//    @Query("select c from Cart c where c.member.memberId = :memberId")
//    List<Cart> findAllByMemberId(Long memberId);

//    @Query("delete from Cart c where c.item.itemId = :itemId and c.member.memberId = :memberId")
//    boolean deleteCartByItemId(Long itemId, Long memberId);
//
//    @Query("delete from Cart c where c.member.memberId = :memberId")
//    boolean deleteAllCartByMemberId(Long memberId);

    @Query("select i from Cart c, Item i where c.member.memberId = :memberId and c.item.itemId = i.itemId")
    List<Item> findAllByMemberId(Long memberId);

    @Query("select c from Cart c where c.member.memberId = :memberId")
    List<Cart> findAllByMemberId1(Long memberId);

    @Query("select c from Cart c where c.item.itemId = :itemId")
    Cart findByItemId(Long itemId);

    @Query("select c from Cart c where c.member.memberId = :memberId and c.status = 1")
    List<Cart> findOrderByMemberId(Long memberId);

    @Query("select i from Cart c, Item i where c.member.memberId=:memberId and i.itemId=c.item.itemId and c.status=1")
    List<Item> findOrderItemByMemberId(Long memberId);
}
