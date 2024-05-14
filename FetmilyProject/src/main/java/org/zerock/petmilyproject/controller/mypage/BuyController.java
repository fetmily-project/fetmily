package org.zerock.petmilyproject.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.zerock.petmilyproject.service.ShopService;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/mypage/buy")
@RequiredArgsConstructor
public class BuyController {
//    ShopService shopService;

    @GetMapping("/list")
    public void orderListGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/addr")
    public void addrGET(){}

    @GetMapping("/like")
    public void likeListGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/service")
    public void serviceGET(){}
}
