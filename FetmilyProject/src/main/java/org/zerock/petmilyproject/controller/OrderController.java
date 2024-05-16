package org.zerock.petmilyproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Orders;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.OrdersDTO;
import org.zerock.petmilyproject.repository.CartRepository;
import org.zerock.petmilyproject.repository.OrdersRepository;
import org.zerock.petmilyproject.service.CartService;
import org.zerock.petmilyproject.service.OrdersService;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    CartRepository cartRepository;
    OrdersRepository ordersRepository;

    CartService cartService;
    OrdersService ordersService;


//    @GetMapping("/")
//    public String getOrder(HttpServletRequest request, Model model) {
////        HttpSession session = request.getSession();
////        OrdersDTO ordersDTO = ordersRepository.findOrdersByMemberId(
////            (Long) session.getAttribute("memberId"));
////        model.addAttribute()
////
////        return null;
//
//
//    }

//    @GetMapping("/complete")
//    public String completeOrder(HttpServletRequest request,Long cartId) {
//        HttpSession session = request.getSession();
//        cartService.orderOK(cartId);
//
//        return "complete";
//    }

}


