package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.MemberDTO;

public interface LogService {
    Long register(MemberDTO memberDTO);
    MemberDTO readOne(Long memberId);
    MemberDTO login(MemberDTO memberDTO);
    void modify(MemberDTO memberDTO);
    void remove(Long bno);
}
