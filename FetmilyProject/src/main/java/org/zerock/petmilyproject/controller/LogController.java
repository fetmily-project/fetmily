package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.member.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("/signup")
    public void signupGET(){}

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody @Valid MemberDTO memberDTO, BindingResult bindingResult) throws BindException {
        String result = logService.register(memberDTO);
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        if("emailExist".equals(result)){
            return ResponseEntity.ok(2);
        }

        if("nicknameExist".equals(result)){
            return ResponseEntity.ok(3);
        }

        return ResponseEntity.ok(1);
    }

    @GetMapping("/login")
    public void loginGET(){}

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpServletRequest httpServletRequest){
        MemberDTO loginMemberDTO = logService.login(memberDTO);

        if(loginMemberDTO == null){
            return ResponseEntity.ok(0);
        }
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("memberId", loginMemberDTO.getMemberId());

        return ResponseEntity.ok(loginMemberDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> memberDelete(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        logService.remove((Long) session.getAttribute("memberId"));

        return ResponseEntity.ok(1);
    }
}
