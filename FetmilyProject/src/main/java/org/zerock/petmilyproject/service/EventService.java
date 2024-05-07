package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.EventDTO;

public interface EventService {
    void addEvent(EventDTO eventDTO);
    void updateEvent(Long eventId, EventDTO eventDTO);
    void deleteEvent(Long eventId);
    EventDTO getEvent(Long eventId);
}
