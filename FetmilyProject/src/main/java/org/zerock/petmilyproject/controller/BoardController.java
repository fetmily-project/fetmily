package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.domain.Board;
import org.zerock.petmilyproject.dto.*;
import org.zerock.petmilyproject.repository.BoardRepository;
import org.zerock.petmilyproject.service.BoardService;
import org.zerock.petmilyproject.service.BoardServiceImpl;
import org.zerock.petmilyproject.service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    private final BoardService boardService;
    private final ReplyService replyService;



    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);

        log.info("controller GET /list에는 image가 들어오나?");
        log.info(responseDTO);
        log.info("dtoList에 BoardListAllDTO안에 boardImage로 들어오네!");

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGet(){

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(false);
        Long loginMemberId = (Long)session.getAttribute("memberId");

        boardDTO.setMemberId(loginMemberId);

        log.info("board POST register......");

        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long boardId = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", boardId);

        return "redirect:/board/list";
    }

//    @GetMapping("/read/{boardId}")
//    public void readGet(@PathVariable Long boardId, PageRequestDTO pageRequestDTO, Model model){
//        ReplyDTO replyDTO = replyService.read(boardId);
//        log.info(replyDTO);
//
//        model.addAttribute("dto", replyDTO);
//
//    }

    @GetMapping({"/read", "/modify"})
    public void read(Long boardId, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.readOne(boardId);

        log.info("controller boardDTO에는 image가 들어가나?");
        log.info(boardDTO);


//        boardServiceImpl.updateViewCnt(boardId);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO,
                         BindingResult bindingResult,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){

        log.info("board modify post......." + boardDTO);

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );

            redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());

            return "redirect:/board/modify?"+link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());

        return "redirect:/board/read";
    }

//    @PostMapping("/remove")
//    public String remove(Long boardId, RedirectAttributes redirectAttributes){
//
//        log.info("remove post.. " + boardId);
//
//        boardService.remove(boardId);
//
//        redirectAttributes.addFlashAttribute("result", "removed");
//
//        return "redirect:/board/list";
//    }

    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {

        Long boardId  = boardDTO.getBoardId();
        log.info("remove post.. " + boardId );

        boardService.remove(boardId);

        log.info(boardDTO.getFileNames());
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";

    }


    public void removeFiles(List<String> files){

        for (String fileName:files) {

            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();


            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();

                if (contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    thumbnailFile.delete();
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
    }
}