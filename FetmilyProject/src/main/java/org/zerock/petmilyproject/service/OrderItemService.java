package org.zerock.petmilyproject.service;

import java.util.List;
import org.zerock.petmilyproject.domain.OrderItem;
import org.zerock.petmilyproject.dto.OrderItemDTO;
import org.zerock.petmilyproject.dto.ItemDTO;

public interface OrderItemService {
    Long register(OrderItemDTO orderItemDTO);
    ItemDTO readOne(Long orderItemId);
    void modify(OrderItemDTO orderItemDTO);
    void remove(Long orderItemId);
    List<OrderItemDTO> readList(Long memberId);

    public int totalPrice(Long memberId);

}
