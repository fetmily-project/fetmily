package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.repository.LogRepository;

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

        return memberId;
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO){
        Member member = modelMapper.map(memberDTO, Member.class);
        Member loginMember = logRepository.login(member.getEmail(), member.getPassword())
                .orElseThrow();

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
