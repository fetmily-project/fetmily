package org.zerock.petmilyproject.service.member;

import java.util.List;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.repository.LogRepository;

public interface LogService {
    String register(MemberDTO memberDTO);
    MemberDTO readOne(Long memberId);
    MemberDTO login(MemberDTO memberDTO);

//    void modify(MemberDTO memberDTO);



    void modifyAddr(MemberDTO memberDTO);
    void modifyPassword(MemberDTO memberDTO);
    void remove(Long bno);

}
