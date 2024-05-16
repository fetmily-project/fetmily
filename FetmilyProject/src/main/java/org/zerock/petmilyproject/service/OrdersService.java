package org.zerock.petmilyproject.service;

import java.util.List;
import org.zerock.petmilyproject.domain.Orders;
import org.zerock.petmilyproject.dto.OrdersDTO;

public interface OrdersService {


    Long order(OrdersDTO ordersDTO);

//    public List<Orders> findOrders(Long memberId);
//    public OrdersDTO getOrder(Long orderId);
//    public void cancelOrder(Long orderId);

}
