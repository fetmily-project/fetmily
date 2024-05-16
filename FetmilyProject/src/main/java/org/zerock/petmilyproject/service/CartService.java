package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;

import java.util.List;

public interface CartService {
    Long register(CartDTO cartDTO);
    CartDTO readOne(Long cartId);
    void modify(CartDTO cartDTO);
    void remove(Long cartId);
//    List<CartDTO> readList(Long memberId);
    void orderOK(Long cartId);
    public int totalPrice(Long memberId);
    List<ItemDTO> readList(Long memberId);
    List<CartDTO> readList1(Long memberId);
}

