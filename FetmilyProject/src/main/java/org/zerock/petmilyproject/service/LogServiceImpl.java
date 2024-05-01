package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.repository.LogRepository;

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
        Member loginMember = logRepository.login(member.getEmail(), member.getPassword());

        return modelMapper.map(loginMember, MemberDTO.class);
    }

    @Override
    public MemberDTO readOne(Long bno) {
        return null;
    }

    @Override
    public void modify(MemberDTO memberDTO) {

    }

    @Override
    public void remove(Long bno) {

    }
}
