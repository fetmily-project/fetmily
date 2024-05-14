package org.zerock.petmilyproject.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.petmilyproject.dto.BoardListAllDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.service.BoardService;
import org.zerock.petmilyproject.service.BoardServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/mypage/post")
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;

    @GetMapping("/mypost")
    public void myPostGET(PageRequestDTO pageRequestDTO, Model model, HttpServletRequest httpServletRequest){
        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/myreview")
    public void myReviewGET(Model model, HttpServletRequest httpServletRequest){

    }

    @GetMapping("/like")
    public void likePostGET(Model model, HttpServletRequest httpServletRequest){

    }
}
