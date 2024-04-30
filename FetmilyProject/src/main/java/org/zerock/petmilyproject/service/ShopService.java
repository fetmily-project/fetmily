package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.ItemDTO;

public interface ShopService {

    Long register(ItemDTO itemDTO);
    ItemDTO readOne(Long bno);
    void modify(ItemDTO itemDTO);
    void remove(Long bno);
}
