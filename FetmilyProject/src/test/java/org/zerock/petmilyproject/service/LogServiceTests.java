package org.zerock.petmilyproject.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.member.LogService;

@SpringBootTest
@Log4j2
public class LogServiceTests {

    @Autowired
    private LogService logService;

    @Test
    public void testRegister(){
        MemberDTO memberDTO = MemberDTO.builder()
            .email("12312311a@naver.com")
            .password("qwer1234")
            .nickname("구름이12")
            .addr("서울특별시 강남구")
            .name("구름이")
            .phone("010-000")

//            .name("구름이")
            .build();
        log.info(logService.register(memberDTO));
    }


    @Test
    public void testRemove(){
        log.info(logService.remove(1L));
    }
}
