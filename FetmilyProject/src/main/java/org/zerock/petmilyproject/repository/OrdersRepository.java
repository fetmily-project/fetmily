package org.zerock.petmilyproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

//    public List<Orders> findAllByString(OrderSearch orderSearch);
        @Query("SELECT o FROM Orders o JOIN o.member m WHERE m.memberId = :memberId")

        List<Orders> findOrdersByMemberId(Long memberId);



}
