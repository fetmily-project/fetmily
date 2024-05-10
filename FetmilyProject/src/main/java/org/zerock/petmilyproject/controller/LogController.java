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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.LogService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<MemberDTO> login(@RequestBody @Valid MemberDTO memberDTO, BindingResult bindingResult) throws BindException{
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        MemberDTO loginMemberDTO = logService.login(memberDTO);

        return ResponseEntity.ok(loginMemberDTO);
    }

    @GetMapping(value = {"/info"})
    public void memberGET(@RequestParam("memberId") Long memberId, Model model){
        MemberDTO memberDTO = logService.readOne(memberId);
        model.addAttribute("memberDTO", memberDTO);
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
