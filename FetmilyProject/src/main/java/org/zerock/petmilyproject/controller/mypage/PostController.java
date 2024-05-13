package org.zerock.petmilyproject.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.petmilyproject.service.BoardService;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/mypage/post")
@RequiredArgsConstructor
public class PostController {
    BoardService boardService;

    @GetMapping("/mypost")
    public void myPostGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/myreview")
    public void myReviewGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/like")
    public void likePostGET(Model model, HttpServletRequest httpServletRequest){

    }
}
