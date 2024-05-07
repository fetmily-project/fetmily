package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.member.MemberDTO;
import org.zerock.petmilyproject.dto.member.RequestDTO;
import org.zerock.petmilyproject.service.LogService;

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
    public ResponseEntity<?> signup(@RequestBody @Valid MemberDTO memberDTO, BindingResult bindingResult) throws BindException{
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        logService.register(memberDTO);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/login")
    public void loginGET(){}

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDTO> login(@RequestBody RequestDTO requestDTO, HttpServletRequest req) throws BindException{

        MemberDTO loginMemberDTO = logService.login(requestDTO);
        HttpSession session = req.getSession();
        session.setAttribute("loginInfo", loginMemberDTO);

        return ResponseEntity.ok(loginMemberDTO);
    }

    @GetMapping(value = {"/info", "/update" })
    public ResponseEntity<MemberDTO> memberGET(@RequestParam("memberId") Long memberId){
        MemberDTO memberDTO = logService.readOne(memberId);

        return ResponseEntity.ok(memberDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> memberDelete(@RequestParam Long memberId){
        logService.remove(memberId);

        return ResponseEntity.ok(1);
    }

    @PutMapping("/update")
    public ResponseEntity<?> memberUpdate(@RequestBody @Valid MemberDTO memberDTO, BindingResult bindingResult) throws BindException{
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        logService.modify(memberDTO);

        return ResponseEntity.ok(1);
    }
}
