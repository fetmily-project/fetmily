package org.zerock.petmilyproject.service.member;

import org.zerock.petmilyproject.dto.MemberDTO;

public interface LogService {
    String register(MemberDTO memberDTO);
    MemberDTO readOne(Long memberId);
    MemberDTO login(MemberDTO memberDTO);
    void modifyAddr(MemberDTO memberDTO);
    void modifyPassword(MemberDTO memberDTO);
    void remove(Long bno);
}
