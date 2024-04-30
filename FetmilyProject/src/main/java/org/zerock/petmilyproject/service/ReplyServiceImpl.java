package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.dto.ReplyDTO;

public class ReplyServiceImpl implements ReplyService{
    @Override
    public Long register(ReplyDTO replyDTO) {
        return null;
    }

    @Override
    public ReplyDTO read(Long rno) {
        return null;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

    }

    @Override
    public void remove(Long rno) {

    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        return null;
    }
}
