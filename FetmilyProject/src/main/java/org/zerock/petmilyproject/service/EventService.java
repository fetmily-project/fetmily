package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.EventDTO;

import java.util.List;

public interface EventService {
    void addEvent(EventDTO eventDTO);

    void updateEvent(Long eventId, EventDTO eventDTO);
    void deleteEvent(Long eventId);

    List<EventDTO> getAllEvents();
}