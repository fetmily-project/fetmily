package org.zerock.petmilyproject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.dto.MemoDTO;
import org.zerock.petmilyproject.service.EventService;
import org.zerock.petmilyproject.service.MemoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
public class CalendarController {

    private final EventService eventService;


    private final MemoService memoService;
    @Autowired
    public CalendarController(EventService eventService, MemoService memoService) {
        this.eventService = eventService;
        this.memoService = memoService;
    }

    @GetMapping("/schedules")
    public String schedulesController() {
        return "/event/calendar";
    }

    @GetMapping("/data/all")
    public ResponseEntity<Map<String, Object>> getAllData() {
        List<EventDTO> events = eventService.getAllEvents();
        List<MemoDTO> memos = memoService.getAllMemo();

        Map<String, Object> response = new HashMap<>();
        response.put("events", events);
        response.put("memos", memos);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public String addData(@RequestBody EventDTO eventDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        log.info(eventDTO);
        eventService.addEvent(eventDTO);
        return "redirect:/schedules";
    }

    @PostMapping("/update/{eventId}")
    public String updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
        eventService.updateEvent(eventId, eventDTO);
        return "redirect:/schedules";
    }

    @PostMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/schedules";
    }
}
