package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.BoardDTO;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
