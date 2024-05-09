package org.zerock.petmilyproject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.service.EventService;

// @RestController: 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
@Controller
@Log4j2

public class CalendarController {

    private final EventService eventService;

    // @Autowired: 스프링에 의존성 주입을 위해 사용됩니다.
    @Autowired
    // CalendarController 생성자, EventService 인스턴스를 주입받습니다.
    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/schedules")
    @RequestMapping(value = "/schedules")
    // @RequestMapping: 클래스 레벨에서 URL을 매핑할 경로를 지정합니다.
    public String schedulesController(){
        return "/event/calendar";
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
        // 받은 eventId를 사용하여 이벤트를 삭제하는 서비스 메서드를 호출합니다.
        eventService.deleteEvent(eventId);
        return "redirect:/schedules";
    }
}