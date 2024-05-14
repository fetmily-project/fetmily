package org.zerock.petmilyproject.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.OrderItem;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.OrderItemDTO;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
        orderItemRepository.save(orderItem);

        return orderItem.getId();
    }

    @Override
    public ItemDTO readOne(Long orderItemId) {
        return null;
    }

    @Override
    public void modify(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(orderItemDTO.getId())
            .orElseThrow();
        orderItem.changeCnt(orderItemDTO.getCount());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void remove(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    public List<OrderItemDTO> readList(Long memberId) {
        List<OrderItem> orderItemList = orderItemRepository.findAllByMemberId(memberId);
        List<OrderItemDTO> orderItemDTOList = orderItemList.stream()
            .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class)).collect(Collectors.toList());

        return orderItemDTOList;
    }

    public int totalPrice(Long memberId) {
        List<OrderItem> orderItemList = orderItemRepository.findAllByMemberId(memberId);
        int totalPrice = orderItemList.stream()
            .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getCount())
            .sum();
        return totalPrice;
    }
}
