package org.zerock.petmilyproject.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.service.CartService;
import org.zerock.petmilyproject.service.ItemService;
//import org.zerock.petmilyproject.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/mypage/buy")
@RequiredArgsConstructor
public class BuyController {
    private final CartService cartService;

    @GetMapping("/list")
    public void orderListGET(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        List<ItemDTO> orderItemList = cartService.readOrderItemList((Long) session.getAttribute("memberId"));

        log.info(orderItemList);

        model.addAttribute("orderItemList", orderItemList);
    }

    @GetMapping("/orderitem")
    public ResponseEntity<List<CartDTO>> orderItemListGET(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        List<CartDTO> orderList = cartService.readOrderList((Long) session.getAttribute("memberId"));

        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/addr")
    public void addrGET(){}

    @GetMapping("/like")
    public void likeListGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/service")
    public void serviceGET(){}
}
