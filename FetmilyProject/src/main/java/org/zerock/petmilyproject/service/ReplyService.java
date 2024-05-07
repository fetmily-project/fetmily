package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long replyId);

    void modify(ReplyDTO replyDTO);

    void remove(Long replyId);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long boardId, PageRequestDTO requestDTO);
}
