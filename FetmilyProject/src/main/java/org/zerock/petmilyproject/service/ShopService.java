package org.zerock.petmilyproject.service;

import org.springframework.data.domain.Page;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.dto.ReplyDTO;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    Long register(ItemDTO itemDTO);
    ItemDTO readOne(Long bno);
    void modify(ItemDTO itemDTO);
    void remove(Long bno);
//    PageResponseDTO<ItemDTO> getListOfItem(PageRequestDTO pageRequestDTO);

    List<ItemDTO> ListOfItemAll();
    List<ItemDTO> ListOfItemByKind(String kind);

    List<ItemDTO> ListOfItemByBrand();
    PageResponseDTO<ItemDTO> searchItem(Optional<String> keyword, PageRequestDTO pageRequestDTO);
//    PageResponseDTO<ItemDTO> searchItem(Optional<String> keyword, PageRequestDTO pageRequestDTO);
//
//    List<ItemDTO> getCategoryListOfItem(String category, String kind);
//    List<ItemDTO> getBrandListOfItem(ItemDTO itemDTO);
//    List<ItemDTO> getListOfNewItem(ItemDTO itemDTO);
}
