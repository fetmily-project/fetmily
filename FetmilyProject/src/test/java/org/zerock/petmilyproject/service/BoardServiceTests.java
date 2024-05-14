//package org.zerock.petmilyproject.service;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.petmilyproject.domain.Member;
//import org.zerock.petmilyproject.dto.BoardDTO;
//import org.zerock.petmilyproject.dto.PageRequestDTO;
//import org.zerock.petmilyproject.dto.PageResponseDTO;
//
//import java.util.Arrays;
//import java.util.UUID;
//
//@SpringBootTest
//@Log4j2
//public class BoardServiceTests {
//
//    @Autowired
//    private BoardService boardService;
//
//    @Test
//    public void testRegister() {
//
//        log.info(boardService.getClass().getName());
//
//        BoardDTO boardDTO = BoardDTO.builder()
//                .title("Sample Title...")
//                .content("Sample Content...")
//                .memberId(1L)
//                .build();
//
//        Long boardId = boardService.register(boardDTO);
//
//        log.info("boardId: " + boardId);
//        log.info("memberId: " + boardDTO.getMemberId());
//    }
//
//    @Test
//    public void testModify() {
//
//        BoardDTO boardDTO = BoardDTO.builder()
//                .boardId(102L)
//                .title("제목 수정")
//                .content("내용 수정")
//                .build();
//
//        boardService.modify(boardDTO);
//    }
//
//    @Test
//    public void testList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .type("tc")
//                .keyword("1")
//                .page(1)
//                .size(10)
//                .build();
//
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//
//        log.info(responseDTO);
//    }
//
//    @Test
//    public void testRegisterWithImages(){
//        log.info(boardService.getClass().getName());
//
//        BoardDTO boardDTO = BoardDTO.builder()
//                .title("File...Sample Title")
//                .content("Sample Content")
//                .memberId(8L)
//                .build();
//
//        boardDTO.setFileNames(
//                Arrays.asList(
//                        UUID.randomUUID() + "_aaa.jpg",
//                        UUID.randomUUID() + "_bbb.jpg",
//                        UUID.randomUUID() + "_bbb.jpg"
//                ));
//
//        Long boardId = boardService.register(boardDTO);
//
//        log.info("boardId: " + boardId);
//    }
//}
