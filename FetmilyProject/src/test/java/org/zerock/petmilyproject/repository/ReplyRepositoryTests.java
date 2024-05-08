package org.zerock.petmilyproject.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.petmilyproject.domain.Board;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.domain.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {

        Long boardId = 226L;


        Board board = Board.builder().boardId(boardId).build();

        Reply reply = Reply.builder()
                .board(board)
                .content("댓글 test2.... ")
                .member(Member.builder()
                        .memberId(8L)
                        .build())
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testBoardReplies() {

        Long boardId = 100L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("replyId").descending());

        Page<Reply> result = replyRepository.listOfBoard(boardId, pageable);

        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }
}
