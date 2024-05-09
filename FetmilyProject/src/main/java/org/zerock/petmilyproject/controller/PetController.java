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
import org.zerock.petmilyproject.dto.PetDTO;
import org.zerock.petmilyproject.service.PetService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/list")
    public ResponseEntity<List<PetDTO>> petList(@RequestParam("memberId") Long memberId){
        List<PetDTO> petlist = petService.petList(memberId);

        return ResponseEntity.ok(petlist);
    }

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> petInsert(@RequestBody @Valid PetDTO petDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        petService.register(petDTO);

        return ResponseEntity.ok(1);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> petUpdate(@RequestBody @Valid PetDTO petDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        petService.modify(petDTO);

        return ResponseEntity.ok(1);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> petDelete(@RequestBody Long petId){
        petService.remove(petId);

        return ResponseEntity.ok(1);
    }

}
