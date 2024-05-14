//package org.zerock.petmilyproject.repository;
//
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//import org.zerock.petmilyproject.domain.Board;
//import org.zerock.petmilyproject.domain.BoardImage;
//import org.zerock.petmilyproject.domain.Member;
//import org.zerock.petmilyproject.dto.BoardListAllDTO;
//import org.zerock.petmilyproject.dto.BoardListReplyCountDTO;
//
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.IntStream;
//
//import static org.zerock.petmilyproject.domain.QBoard.board;
//
//@SpringBootTest
//@Log4j2
//public class BoardRepositoryTests {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Autowired
//    private ReplyRepository boardReplyRepository;
//    @Autowired
//    private ReplyRepository replyRepository;
//
//    @Test
//    public void testInsert() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Board board = Board.builder()
//                    .title("pagenation 테스트" + i)
//                    .content("구름이는 귀엽습니다." + i)
//                    .member(Member.builder()
//                            .memberId(3L)
//                            .build())
//                    .build();
//
//            Board result = boardRepository.save(board);
//            log.info("BoardID: " + result.getBoardId());
//        });
//    }
//
//    @Test
//    public void testSelect() {
//        Long boardId = 1L;
//
//        Optional<Board> result = boardRepository.findById(boardId);
//
//        Board board = result.get();
//
//        log.info(board);
//    }
//
//    @Test
//    public void testSearchAll() {
//
//        String[] types = {"t", "c", "n"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("boardId").descending());
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
//
//        log.info(result.getTotalPages());
//
//        log.info(result.getSize());
//
//        log.info(result.getNumber());
//
//        log.info(result.hasPrevious() + ": " + result.hasNext());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
//
//    @Test
//    public void testSearchReplyCount() {
//
//        String[] types = {"t", "c", "n"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("boardId").descending());
//
//        Page<BoardListReplyCountDTO> result = boardRepository.
//                searchWithReplyCount(types, keyword, pageable);
//
//        log.info(result.getTotalPages());
//
//        log.info(result.getSize());
//
//        log.info(result.getNumber());
//
//        log.info(result.hasPrevious() + ": " + result.hasNext());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
//
//    @Test
//    public void testInsertWithImages() {
//
//        Board board = Board.builder()
//                .title("Image Test")
//                .content("첨부파일 테스트")
//                .member(Member.builder()
//                        .memberId(8L)
//                        .build())
//                .build();
//
//        for (int i=0; i<3; i++) {
//
//            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
//
//        }
//
//        boardRepository.save(board);
//    }
//
////    @Transactional
//    @Test
//    public void testReadWithImages() {
//
//        Optional<Board> result = boardRepository.findById(113L);
//
//        Board board = result.orElseThrow();
//
//        log.info(board);
//        log.info("--------------");
//        for (BoardImage boardImage : board.getImageSet()) {
//            log.info(boardImage);
//        }
//    }
//
//    @Transactional
//    @Commit
//    @Test
//    public void testModifyImages() {
//
//        Optional<Board> result = boardRepository.findByIdWithImages(113L);
//
//        Board board = result.orElseThrow();
//
//        //기존의 첨부파일들은 삭제
//        board.clearImage();
//
//        //새로운 첨부파일들
//        for (int i = 0; i < 2; i++) {
//
//            board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
//        }
//
//        boardRepository.save(board);
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    public void testRemoveAll() {
//
//        Long boardId = 1L;
//
//        replyRepository.deleteByBoard_BoardId(boardId);
//
//        boardRepository.deleteById(boardId);
//    }
//
//    @Transactional
//    @Test
//    public void testSearchImageReplyCount() {
//
//        Pageable pageable = PageRequest.of(0,10,Sort.by("boardId").descending());
//
//        //boardRepository.searchWithAll(null, null,pageable);
//
//        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null,null,pageable);
//
//        log.info("---------------------------");
//        log.info(result.getTotalElements());
//
//        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
//
//
//    }
//}
