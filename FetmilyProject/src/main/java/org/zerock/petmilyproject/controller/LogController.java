package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.MemberDTO;
import org.zerock.petmilyproject.service.LogService;

@Log4j2
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
    public String login(@RequestBody MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        MemberDTO loginMemberDTO = logService.login(memberDTO);
        redirectAttributes.addAttribute("loginMemberDTO", loginMemberDTO);

        return "redirect:/member/login";
    }

    @GetMapping(value = {"/info?memberId={memberId}", "/update" })
    public void memberGET(@PathVariable("memberId") Long memberId, Model model){
        MemberDTO memberDTO = logService.readOne(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @DeleteMapping("/delete?memberId={memberId}")
    public String memberDelete(@PathVariable("memberId") Long memberId){
        logService.remove(memberId);

        return null;
    }

    @PostMapping("/update")
    public String memberUpdate(@RequestBody MemberDTO memberDTO){
        logService.modify(memberDTO);

        return null;
    }
}
