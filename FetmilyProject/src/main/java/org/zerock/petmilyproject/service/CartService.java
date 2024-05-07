package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;

import java.util.List;

public interface CartService {
    Long register(CartDTO cartDTO);
    ItemDTO readOne(Long cartId);
    void modify(CartDTO cartDTO);
    void remove(Long cartId);
    List<CartDTO> readList(Long memberId);
}
