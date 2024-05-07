package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.member.MemberDTO;
import org.zerock.petmilyproject.dto.member.RequestDTO;

public interface LogService {
    Long register(MemberDTO memberDTO);
    MemberDTO readOne(Long memberId);
    MemberDTO login(RequestDTO requestDTO);
    void modify(MemberDTO memberDTO);
    void remove(Long bno);
}
