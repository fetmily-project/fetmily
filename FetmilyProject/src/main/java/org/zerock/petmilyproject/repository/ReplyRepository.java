package org.zerock.petmilyproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.boardId = :boardId")
    Page<Reply> listOfBoard(Long boardId, Pageable pageable);

    void deleteByBoard_BoardId(Long boardId);
}
