package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.service.ItemService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ItemService itemService;

    //    @GetMapping("/list")
//    public ResponseEntity<PageResponseDTO<ItemDTO>> shopGET(PageRequestDTO pageRequestDTO){
//        PageResponseDTO<ItemDTO> responseDTO = shopService.getListOfItem(pageRequestDTO);
//
//        return ResponseEntity.ok(responseDTO);
//    }
    @GetMapping("/list")
    public void shopGET(){
        List<ItemDTO> itemDTO = itemService.ListOfItemAll();
    }
    @GetMapping("/list/all")
    public ResponseEntity<List<ItemDTO>> itemListAll(){
        List<ItemDTO> itemDTO = itemService.ListOfItemAll();

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/list/dog")
    public ResponseEntity<List<ItemDTO>> itemListDog(){
        List<ItemDTO> itemDTO = itemService.ListOfItemByKind("dog");

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/list/cat")
    public ResponseEntity<List<ItemDTO>> itemListCat(){
        List<ItemDTO> itemDTO = itemService.ListOfItemByKind("cat");

        return ResponseEntity.ok(itemDTO);
    }
    @GetMapping("/list/brand")
    public ResponseEntity<List<ItemDTO>> itemListByBrand(){
        List<ItemDTO> itemDTO = itemService.ListOfItemByBrand();

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/list/search")
    public ResponseEntity<PageResponseDTO<ItemDTO>> searchItem(@RequestParam Optional<String> keyword, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ItemDTO> responseDTO = itemService.searchItem(keyword, pageRequestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/detail")
    public ResponseEntity<ItemDTO> itemDetail(@RequestParam Long itemId){
        ItemDTO itemDTO = itemService.readOne(itemId);

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/item/{id}") // 상품 상세정보
    public String getProductDetail(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.readOne(id);
        model.addAttribute("item", itemDTO);
        return "shop/item_detail";
    }

}
