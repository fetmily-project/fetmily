package org.zerock.petmilyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.petmilyproject.dto.PetDTO;
import org.zerock.petmilyproject.service.pet.PetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/list")
    public ResponseEntity<List<PetDTO>> petList(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        List<PetDTO> petlist = petService.petList((Long) session.getAttribute("memberId"));

        return ResponseEntity.ok(petlist);
    }

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> petInsert(@RequestBody @Valid PetDTO petDTO, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        HttpSession session = httpServletRequest.getSession(false);
        petDTO.setMemberId((Long) session.getAttribute("memberId"));

        petService.register(petDTO);

        return ResponseEntity.ok(1);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
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
