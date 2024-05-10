package org.zerock.petmilyproject.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Event;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void addEvent(EventDTO eventDTO) {
        // DTO로부터 엔티티 생성
        Event event = Event.builder()
                .content(eventDTO.getContent())
                .eventColor(eventDTO.getEventColor())
                .startDate(eventDTO.getStartDate())
                .endDate(eventDTO.getEndDate())
                .cycle(eventDTO.getCycle())
                .build();

        // 엔티티 저장
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long eventId, EventDTO eventDTO) {
        // 이벤트 ID로 기존 이벤트 조회
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            // 업데이트할 필드 설정
            event.setContent(eventDTO.getContent());
            event.setEventColor(eventDTO.getEventColor());
            event.setStartDate(eventDTO.getStartDate());
            event.setEndDate(eventDTO.getEndDate());
            event.setCycle(eventDTO.getCycle());

            // 엔티티 업데이트
            eventRepository.save(event);
            // 변경 사항은 트랜잭션 커밋 시점에 자동으로 반영됨
        } else {
            // 해당 이벤트가 존재하지 않는 경우 처리
            throw new IllegalArgumentException("Event with ID " + eventId + " not found");
        }
    }



    @Override
    public void deleteEvent(Long eventId) {
        // 이벤트 ID로 이벤트 삭제
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDTO getEvent(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            // 엔티티를 DTO로 변환하여 반환
            return EventDTO.builder()
                    .content(event.getContent())
                    .eventColor(event.getEventColor())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .cycle(event.getCycle())
                    .build();
        } else {
            // 해당 이벤트가 존재하지 않는 경우 처리
            throw new IllegalArgumentException("Event with ID " + eventId + " not found");
        }


    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> EventDTO.builder()
                        .content(event.getContent())
                        .eventColor(event.getEventColor())
                        .startDate(event.getStartDate())
                        .endDate(event.getEndDate())
                        .cycle(event.getCycle())
                        .build())
                .collect(Collectors.toList());
    }
}
