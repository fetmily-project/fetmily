package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        cartRepository.save(cart);

        return cart.getCartId();
    }

    @Override
    public ItemDTO readOne(Long cartId) {
        return null;
    }

    @Override
    public void modify(CartDTO cartDTO) {
        Cart cart = cartRepository.findById(cartDTO.getCartId())
            .orElseThrow();
        cart.changeCnt(cartDTO.getCnt());
        cartRepository.save(cart);
    }

    @Override
    public void remove(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public List<CartDTO> readList(Long memberId) {
        List<Cart> cartList = cartRepository.findAllByMemberId(memberId);
        List<CartDTO> cartDTOList = cartList.stream()
            .map(cartItem -> modelMapper.map(cartItem, CartDTO.class)).collect(Collectors.toList());

        return cartDTOList;
    }

    @Override
    public List<CartDTO> readOrderList(Long memberId) {
        List<CartDTO> orderList = cartRepository.findOrderByMemberId(memberId).stream()
                .map(order -> modelMapper.map(order, CartDTO.class)).collect(Collectors.toList());

        return orderList;
    }

    @Override
    public List<ItemDTO> readOrderItemList(Long memberId) {
        List<ItemDTO> orderItemList = cartRepository.findOrderItemByMemberId(memberId).stream()
                .map(orderItem -> modelMapper.map(orderItem, ItemDTO.class)).collect(Collectors.toList());

        return orderItemList;
    }

    public int totalPrice(Long memberId){
        List<Cart> cartList = cartRepository.findAllByMemberId(memberId);
        return 1;
    }
}
