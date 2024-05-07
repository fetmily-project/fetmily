package org.zerock.petmilyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.service.EventService;

@RestController
@RequestMapping("/schedules")
public class CalendarController {

    @Autowired
    private EventService EventService;

    @PostMapping
    public ResponseEntity<String> addSchedule(@RequestBody EventDTO eventDTO) {
        try {
            EventService.addEvent(eventDTO); // 서비스로 DTO 전달하여 스케줄 추가
            return ResponseEntity.ok("Schedule added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add schedule");
        }
    }
}
