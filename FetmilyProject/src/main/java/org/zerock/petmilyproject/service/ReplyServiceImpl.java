package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Board;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.domain.Reply;
import org.zerock.petmilyproject.dto.PageRequestDTO;
import org.zerock.petmilyproject.dto.PageResponseDTO;
import org.zerock.petmilyproject.dto.ReplyDTO;
import org.zerock.petmilyproject.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = Reply.builder()
                        .board(Board.builder()
                                .boardId(replyDTO.getBoardId())
                                .build())
                        .member(Member.builder()
                                .memberId(replyDTO.getMemberId())
                                .build())
                        .content(replyDTO.getContent())
                                .build();


        log.info(reply);
        log.info("ReplyServiceImpl reply 확인");

        Long replyId = replyRepository.save(reply).getReplyId();
        log.info(replyId);
        log.info("ReplyServiceImpl replyId 확인");

        return replyId;
    }
    @Override
    public ReplyDTO read(Long replyId) {

        Optional<Reply> replyOptional = replyRepository.findById(replyId);

        Reply reply = replyOptional.orElseThrow();

        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getReplyId());

        Reply reply = replyOptional.orElseThrow();

        reply.changeText(replyDTO.getContent());

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long replyId) {

        replyRepository.deleteById(replyId);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long boardId, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0:
                        pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("replyId").ascending());

        Page<Reply> result = replyRepository.listOfBoard(boardId, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());
        log.info(dtoList);

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}