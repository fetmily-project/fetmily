package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.dto.member.MemberDTO;
import org.zerock.petmilyproject.dto.member.RequestDTO;
import org.zerock.petmilyproject.exception.CustomException;
import org.zerock.petmilyproject.repository.LogRepository;
import org.zerock.petmilyproject.common.enums.Error;

@Log4j2
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService{
    private final ModelMapper modelMapper;
    private final LogRepository logRepository;

    @Override
    public Long register(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        Long memberId = logRepository.save(member).getMemberId();
        log.info(member);
        return memberId;
    }

    @ResponseBody
    @Override
    public MemberDTO login(RequestDTO requestDTO){
        Member loginMember = logRepository.login(requestDTO.getEmail(), requestDTO.getPassword())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        MemberDTO loginMemberDTO = MemberDTO.builder()
                .memberId(loginMember.getMemberId())
                .nickname(loginMember.getNickname())
                .build();

        log.info(loginMemberDTO);

        return loginMemberDTO;
    }

    @Override
    public MemberDTO readOne(Long memberId) {
        Member member = logRepository.findByMemberId(memberId)
                .orElseThrow();

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .addr(member.getAddr())
                .name(member.getName())
                .phone(member.getPhone())
                .build();

        return memberDTO;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        logRepository.save(member);
    }

    @Override
    public void remove(Long memberId) {
        logRepository.deleteById(memberId);
    }
}
