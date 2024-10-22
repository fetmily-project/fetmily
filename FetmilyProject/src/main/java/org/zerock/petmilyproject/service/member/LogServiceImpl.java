package org.zerock.petmilyproject.service.member;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.repository.LogRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final ModelMapper modelMapper;
    private final LogRepository logRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String register(MemberDTO memberDTO) {
        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
            .addr(memberDTO.getAddr())
            .name(memberDTO.getName())
            .phone(memberDTO.getPhone())

                .build();

        Boolean isExistEmail = logRepository.existsByEmail(member.getEmail());
        Boolean isExistNickname = logRepository.existsByNickname(member.getNickname());

        if(isExistEmail){
            return "emailExist";
        }

        if(isExistNickname){
            return "nicknameExist";
        }

        logRepository.save(member);
        return "ok";
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO){
        Member member = modelMapper.map(memberDTO, Member.class);

        Member loginMember = logRepository.findByEmail(member.getEmail())
                .orElseThrow();

        if(bCryptPasswordEncoder.matches(member.getPassword(), loginMember.getPassword())){
            MemberDTO loginMemberDTO = MemberDTO.builder()
                    .memberId(loginMember.getMemberId())
                    .build();

            return loginMemberDTO;
        }

        return null;
    }

    @Override
    public MemberDTO readOne(Long memberId) {
        Member member = logRepository.findByMemberId(memberId)
                .orElseThrow();

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .addr(member.getAddr())
                .name(member.getName())
                .phone(member.getPhone())
//                .socialNumber(member.getSocialNumber())
                .build();

        return memberDTO;
    }

    @Override
    public void modifyAddr(MemberDTO memberDTO) {
        // memberId값으로 db에서 정보 가져와서 addr값만 change
        Member member = logRepository.findByMemberId(memberDTO.getMemberId())
                .orElseThrow();

        member.changeAddr(memberDTO);

        logRepository.save(member);
    }

    @Override
    public void modifyPassword(MemberDTO memberDTO) {
        // memberId값으로 db에서 정보 가져와서 addr값만 change
        Member member = logRepository.findByMemberId(memberDTO.getMemberId())
                .orElseThrow();

        member.changePassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));

        logRepository.save(member);
    }

    @Override
    public void remove(Long memberId) {
        logRepository.deleteById(memberId);
    }
//
//    @Override
//    public List<Member> findMembers() {
//        return logRepository.findAll();
//    }
}
