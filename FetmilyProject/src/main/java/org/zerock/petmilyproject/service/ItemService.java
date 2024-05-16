package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Long register(ItemDTO itemDTO);
    ItemDTO readOne(Long bno);
    void modify(ItemDTO itemDTO);
    void remove(Long bno);
//    PageResponseDTO<ItemDTO> getListOfItem(PageRequestDTO pageRequestDTO);

    public List<Item> findItems();
    List<ItemDTO> ListOfItemAll();
    List<ItemDTO> ListOfItemByKind(String kind);

    List<ItemDTO> ListOfItemByBrand(String brand);

//    List<ItemDTO> ListOfItemByMemberId(Long memberId);
    List<ItemDTO> ListOfItemByCartId(List<Long> orderList);
    List<ItemDTO> searchItem(Optional<String> keyword);
//    PageResponseDTO<ItemDTO> searchItem(Optional<String> keyword, PageRequestDTO pageRequestDTO);
//
//    List<ItemDTO> getCategoryListOfItem(String category, String kind);
//    List<ItemDTO> getBrandListOfItem(ItemDTO itemDTO);
//    List<ItemDTO> getListOfNewItem(ItemDTO itemDTO);
}
