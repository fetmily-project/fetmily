package org.zerock.petmilyproject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.MemoDTO;
import org.zerock.petmilyproject.service.MemoService;

import java.util.List;

@Controller
@Log4j2
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memoSchedules")
    public String schedulesController() {
        return "event/calendar";
    }

    @GetMapping("/memo/memoAll")
    @ResponseBody
    public List<MemoDTO> getAllMemo() {
        return memoService.getAllMemo();
    }


    @PostMapping("/memoAdd")
    public String addData(@RequestBody MemoDTO memoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Received memoDTO: {}", memoDTO);
        if (bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        log.info(memoDTO);
        memoService.addMemo(memoDTO);
        return "redirect:/memoSchedules";
    }

    @PostMapping("/memoUpdate/{memoId}")
    public String updateMemo(@PathVariable Long memoId, @RequestBody MemoDTO memoDTO) {
        memoService.updateMemo(memoId, memoDTO);
        return "redirect:/memoSchedules";
    }

    @PostMapping("/memoDelete/{memoId}")
    public String deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        return "redirect:/memoSchedules";
    }


}