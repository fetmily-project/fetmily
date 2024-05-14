package org.zerock.petmilyproject.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.domain.Orders;
import org.zerock.petmilyproject.dto.OrdersDTO;
import org.zerock.petmilyproject.service.ItemService;
import org.zerock.petmilyproject.service.OrdersService;
import org.zerock.petmilyproject.service.member.LogService;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final LogService logService;
    private final ItemService itemService;

//    @GetMapping("/order")
//    public String createForm(Model model) {
//
////        List<Member> members = logService.findMembers();
//        List<Item> items = itemService.findItems();
//
////        model.addAttribute("members", members);
//        model.addAttribute("items", items);
//
//        return "order/orderForm";
//    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
        @RequestParam("itemId") Long itemId,
        @RequestParam("count") int count) {
        ordersService.order(memberId, itemId, count);
        return "redirect:/orders";

    }
    @GetMapping("/orders")
    public String orderList(Long memberId, Model model) {
        List<Orders> ordersDTO = ordersService.findOrders(memberId);
        model.addAttribute("orders", ordersDTO);

        return "order/orderList";
    }

}
