package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.LogService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("/signup")
    public void signupGET(){}

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberDTO memberDTO){
        logService.register(memberDTO);

        return "redirect:";
    }

    @GetMapping("/login")
    public void loginGET(){}

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody MemberDTO memberDTO){
        MemberDTO loginMemberDTO = logService.login(memberDTO);
        System.out.println(loginMemberDTO);
        return "redirect:";
    }

}
