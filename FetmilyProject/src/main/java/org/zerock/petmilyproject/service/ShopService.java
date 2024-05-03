package org.zerock.petmilyproject.service;

import org.springframework.data.domain.Page;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.dto.ReplyDTO;

import java.util.List;

public interface ShopService {

    Long register(ItemDTO itemDTO);
    ItemDTO readOne(Long bno);
    void modify(ItemDTO itemDTO);
    void remove(Long bno);
    PageResponseDTO<ItemDTO> getListOfItem(PageRequestDTO pageRequestDTO);
}
