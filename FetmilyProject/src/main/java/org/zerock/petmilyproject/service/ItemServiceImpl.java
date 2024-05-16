package org.zerock.petmilyproject.service;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.dto.*;
import org.zerock.petmilyproject.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public ItemDTO readOne(Long bno) {
        Optional<Item> item = itemRepository.findById(bno);
        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
        return itemDTO;
    }

    @Override
    public void modify(ItemDTO itemDTO) {

    }

    @Override
    public void remove(Long bno) {

    }

//    @Override
//    public PageResponseDTO<ItemDTO> getListOfItem(PageRequestDTO pageRequestDTO) { // 상품 리스트
//        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
//                pageRequestDTO.getSize(),
//                Sort.by("itemId").ascending());
//
//        Page<Item> result = shopRepository.listOfItem(pageable);
//
//        List<ItemDTO> dtoList = result.getContent().stream().map(item -> modelMapper.map(item, ItemDTO.class))
//                    .collect(Collectors.toList());
//
//        return PageResponseDTO.<ItemDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total((int)result.getTotalElements())
//                .build();
//    }
//@Override
//public List<ItemDTO> searchItem(Optional<String> keyword) { // 이름으로 상품 검색
//    Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
//        pageRequestDTO.getSize(),
//        Sort.by("itemId").ascending());
//
//    Page<Item> result = itemRepository.listOfSearchItem(keyword, pageable);
//
//    List<ItemDTO> dtoList = result.getContent().stream().map(item -> modelMapper.map(item, ItemDTO.class))
//        .collect(Collectors.toList());
//
//    return PageResponseDTO.<ItemDTO>withAll()
//        .pageRequestDTO(pageRequestDTO)
//        .dtoList(dtoList)
//        .total((int)result.getTotalElements())
//        .build();
//}
    @Override
    public List<ItemDTO> searchItem(Optional<String> keyword) { // 이름으로 상품 검색

        List<Item> result = itemRepository.listOfSearchItem(keyword);

        List<ItemDTO> dtoList = result.stream().map(item -> modelMapper.map(item, ItemDTO.class))
            .collect(Collectors.toList());

        return dtoList;
    }

    public List<ItemDTO> ListOfItemAll() { // 전체 상품 리스트

        List<Item> itemList = itemRepository.listOfItem();
//        List<ItemDTO> itemDTOList = shopRepository.listOfItem();
        List<ItemDTO> itemDTOList = itemList.stream()
            .map(vo -> modelMapper.map(vo,ItemDTO.class))
            .collect(Collectors.toList());
        return itemDTOList;
    }

    @Override
    public List<ItemDTO> ListOfItemByKind(String kind) { // 아이콘으로 kind에 해당하는 상품 리스트 불러오기
        List<Item> itemList = itemRepository.ListOfItemByKind(kind);
        List<ItemDTO> itemDTOList = itemList.stream()
            .map(vo -> modelMapper.map(vo,ItemDTO.class))
            .collect(Collectors.toList());
        return itemDTOList;
    }

    @Override
    public List<ItemDTO> ListOfItemByBrand(String brand) {
        if(brand.equals("All")){
            List<Item> itemList = itemRepository.ListOfItemByBrandAll();
            List<ItemDTO> itemDTOList = itemList.stream()
                .map(vo -> modelMapper.map(vo,ItemDTO.class))
                .collect(Collectors.toList());

            return itemDTOList;
        }

        List<Item> itemList = itemRepository.ListOfItemByBrand(brand);
        List<ItemDTO> itemDTOList = itemList.stream()
            .map(vo -> modelMapper.map(vo,ItemDTO.class))
            .collect(Collectors.toList());


        return itemDTOList;
    }

//    @Override
//    public List<ItemDTO> ListOfItemByMemberId(Long memberId) {
//        List<Item> itemList = itemRepository.ListOfItemByCart(memberId);
//        List<ItemDTO> itemDTOList = itemList.stream()
//            .map(vo -> modelMapper.map(vo,ItemDTO.class))
//            .collect(Collectors.toList());
//
//        return itemDTOList;
//    }


    @Override
    public List<ItemDTO> ListOfItemByCartId(List<Long> orderList) {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(Long num : orderList){
            Item itemList = itemRepository.ListOfItemByCartAll(num);
            ItemDTO itemDTO = modelMapper.map(itemList, ItemDTO.class);
            itemDTOList.add(itemDTO);
        }

        return itemDTOList;
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.listOfItem();
    }
    //    @Override
//    public List<ItemDTO> getCategoryListOfItem(Optional<String> category, Optional<String> kind) { // 상품 리스트
//
////        ItemDTO itemDTO = new ItemDTO();
////        itemDTO.setCategory(category.get());
////        itemDTO.setKind(kind.get());
////        Item item = modelMapper.map(itemDTO, Item.class);
//
//        List<Item> itemList = shopRepository.categoryListOfItem(category, kind);
////        List<Item> itemList = shopRepository.categoryListOfItem("사료", "개");
//
//        List<ItemDTO> result = itemList.stream()
//            .map(vo -> modelMapper.map(vo, ItemDTO.class))
//            .collect(Collectors.toList());
//
//        log.info(result);
//
//        return result;
//    }

//    @Override
//    public List<ItemDTO> getBrandListOfItem(ItemDTO itemDTO ) { // 상품 리스트
//        return null;
//    }
//
//    @Override
//    public List<ItemDTO> getListOfNewItem(ItemDTO itemDTO) { // 상품 리스트
//
//
//        return null;
//    }


}
