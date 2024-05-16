package org.zerock.petmilyproject.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.CartService;

import java.util.List;
import org.zerock.petmilyproject.service.ItemService;
import org.zerock.petmilyproject.service.member.LogService;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final LogService logService;
    private final ItemService itemService;
    private final CartService cartService;

//    @GetMapping("/cart")
//    public List<CartDTO> getCartList(@RequestParam Long memberId){
//        List<CartDTO> cartDTOList = cartService.readList1(memberId);
//
//        return cartDTOList;
//    }

//    @PostMapping("/insert")
//    public ResponseEntity<?> insertCartItem(@RequestBody CartDTO cartDTO){
//        cartService.register(cartDTO);
//
//        return ResponseEntity.ok(1);
//    }

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

//    @GetMapping ("/cart")
//    public void getCartList(HttpSession session, Model model){
//        List<ItemDTO> itemDTOList = cartService.readList((Long) session.getAttribute("memberId"));
//
//        model.addAttribute("itemDTOList", itemDTOList);
//    }

    @GetMapping ("/cart")
    public String getCartList(HttpSession session, Model model){
        List<ItemDTO> itemDTOList = cartService.readList((Long) session.getAttribute("memberId"));

        if (itemDTOList == null || itemDTOList.isEmpty()) {
            return "cart/cart_null"; // ItemDTO가 null이거나 비어있으면 null.html 페이지로 이동
        }
            model.addAttribute("itemDTOList", itemDTOList);
            return "cart/cart"; // ItemDTO가 존재하면 list.html 페이지로 이동

    }
    @GetMapping("/cnt")
    public ResponseEntity<?> getCnt(HttpSession session){
        List<CartDTO> cartDTOList = cartService.readList1((Long) session.getAttribute("memberId"));

        return ResponseEntity.ok(cartDTOList);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertCartItem(@RequestBody CartDTO cartDTO, HttpSession session){
        cartDTO.setMemberId((Long) session.getAttribute("memberId"));
        cartService.register(cartDTO);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/order")
    public void getOrder(HttpServletRequest request, @RequestParam List<Long> orderList,Model model) {
        HttpSession session = request.getSession();
        System.out.println("orderList============="+orderList);
        MemberDTO memberDTO = logService.readOne(((Long) session.getAttribute("memberId")));
        System.out.println("memberDTO========"+memberDTO);
        List<ItemDTO> itemDTOList = itemService.ListOfItemByCartId(orderList);

        List<CartDTO> cartDTOList = new ArrayList<>();
        for(Long cartId : orderList) {
            CartDTO cartDTO = cartService.readOne(cartId);
            cartDTOList.add(cartDTO);
        }
        model.addAttribute("cartDTOList", cartDTOList);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("memberDTO", memberDTO);
    }
    @GetMapping("/order/ok")
    public String completeOrder(){
        return "cart/ok";
    }


}
