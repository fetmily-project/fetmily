package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.service.CartService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping()
    public List<CartDTO> getCartList(@RequestParam Long memberId){
        List<CartDTO> cartDTOList = cartService.readList(memberId);
        return cartDTOList;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertCartItem(@RequestBody CartDTO cartDTO){
        cartService.register(cartDTO);
        log.info(cartDTO);
        return ResponseEntity.ok(1);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCartItem(@RequestBody CartDTO cartDTO){
        cartService.modify(cartDTO);
        return ResponseEntity.ok(1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCartItem(@RequestBody Long cartId){
        cartService.remove(cartId);

        return ResponseEntity.ok(1);
    }

}
