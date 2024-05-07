package org.zerock.petmilyproject.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.service.ShopService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<ItemDTO>> shopGET(PageRequestDTO pageRequestDTO){
        PageResponseDTO<ItemDTO> responseDTO= shopService.getListOfItem(pageRequestDTO);

        return ResponseEntity.ok(responseDTO);
    }

}
