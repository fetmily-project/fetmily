package org.zerock.petmilyproject.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.domain.OrderItem;
import org.zerock.petmilyproject.domain.Orders;
import org.zerock.petmilyproject.dto.OrdersDTO;
import org.zerock.petmilyproject.repository.LogRepository;
import org.zerock.petmilyproject.repository.ItemRepository;
import org.zerock.petmilyproject.repository.OrdersRepository;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final LogRepository logRepository;
    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;

    @Override
    public Long order(Long memberId, long itemId, int count){

        Member member = logRepository.findByMemberId(memberId)
            .orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();


        OrderItem orderItem = OrderItem.createOrderItem(member, item, item.getPrice(), count);


        Orders orders = Orders.createOrders(member, orderItem);

        ordersRepository.save(orders);

        return orders.getOrderId();


    }

    // 주문 조회
//
//    public OrdersDTO getOrder(Long orderId) {}
//        Orders orders = orders
//
//

    @Transactional // 주문 취소
    public void cancelOrder(Long orderId){
        Orders orders = ordersRepository.findById(orderId).get();
        orders.cancel();
    }

//    public List<Orders> findOrders(OrderSearch orderSearch){
//        return ordersRepository.findAllByString(orderSearch);
//    }

    @Override // 주문 목록 조회
    public List<Orders> findOrders(Long memberId) {
        List<Orders> ordersList = ordersRepository.findOrdersByMemberId(memberId);
        return  ordersList;
    }
}

