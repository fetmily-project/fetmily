package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.PetDTO;
import org.zerock.petmilyproject.service.PetService;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/list")
    public void petList(@RequestParam("memberId") Long memberId, Model model){
        List<PetDTO> petlist = petService.petList(memberId);
        model.addAttribute("petList", petlist);
    }

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String petInsert(@RequestBody PetDTO petDTO, RedirectAttributes redirectAttributes){
        petService.register(petDTO);
        redirectAttributes.addAttribute("memberId", petDTO.getMemberId());
        return "redirect:/pet/list";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String petUpdate(@RequestBody PetDTO petDTO, RedirectAttributes redirectAttributes){
        petService.modify(petDTO);

        return "redirect:/pet/read";
    }

}
