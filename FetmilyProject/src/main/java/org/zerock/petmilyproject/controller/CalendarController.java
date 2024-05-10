//package org.zerock.petmilyproject.controller;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.zerock.petmilyproject.dto.EventDTO;
//import org.zerock.petmilyproject.service.EventService;
//
//import java.util.List;
//
//@Controller
//@Log4j2
//public class CalendarController {
//
//    private final EventService eventService;
//
//    @Autowired
//    public CalendarController(EventService eventService) {
//        this.eventService = eventService;
//    }
//
//    @GetMapping("/schedules")
//    public String schedulesController() {
//        return "event/calendar";
//    }
//
//    @GetMapping("/events/all")
//    @ResponseBody
//    public List<EventDTO> getAllEvents() {
//        return eventService.getAllEvents();
//    }
//
//    @PostMapping("/add")
//    public String addData(@RequestBody EventDTO eventDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            log.info("has errors......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//        }
//        log.info(eventDTO);
//        eventService.addEvent(eventDTO);
//        return "redirect:/schedules";
//    }
//
//    @PostMapping("/update/{eventId}")
//    public String updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
//        eventService.updateEvent(eventId, eventDTO);
//        return "redirect:/schedules";
//    }
//
//    @PostMapping("/delete/{eventId}")
//    public String deleteEvent(@PathVariable Long eventId) {
//        eventService.deleteEvent(eventId);
//        return "redirect:/schedules";
//    }
//}
