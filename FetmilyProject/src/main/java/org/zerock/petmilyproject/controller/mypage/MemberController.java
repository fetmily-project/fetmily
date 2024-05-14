package org.zerock.petmilyproject.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.member.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/mypage/member")
@RequiredArgsConstructor
public class MemberController {
    private final LogService logService;

    @GetMapping(value = {"/info", "/update"})
    public void memberInfoGET(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        MemberDTO memberDTO = logService.readOne((Long) session.getAttribute("memberId"));
        model.addAttribute("memberDTO", memberDTO);
    }

    @PutMapping("/updateAddr")
    public ResponseEntity<?> updateAddr(@RequestBody MemberDTO memberDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        memberDTO.setMemberId((Long) session.getAttribute("memberId"));
        logService.modifyAddr(memberDTO);

        return ResponseEntity.ok(1);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody MemberDTO memberDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        memberDTO.setMemberId((Long) session.getAttribute("memberId"));
        logService.modifyPassword(memberDTO);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/delete")
    public void memberDeleteGET(){}

    @DeleteMapping("/delete")
    public ResponseEntity<?> memberDelete(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        logService.remove((Long) session.getAttribute("memberId"));

        return ResponseEntity.ok(1);
    }

}
