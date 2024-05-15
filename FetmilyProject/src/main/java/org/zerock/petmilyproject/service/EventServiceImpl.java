package org.zerock.petmilyproject.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Event;
import org.zerock.petmilyproject.domain.Memo;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.dto.MemoDTO;
import org.zerock.petmilyproject.repository.EventRepository;
import org.zerock.petmilyproject.repository.MemoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final MemoRepository memoRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, MemoRepository memoRepository) {
        this.eventRepository = eventRepository;
        this.memoRepository = memoRepository;
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
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll(); // 모든 이벤트 데이터 가져오기
        return events.stream()
                .map(this::convertToDTO) // 엔티티를 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 변환하여 반환
    }

    public List<MemoDTO> getAllMemo() {
        List<Memo> memos = memoRepository.findAll();
        return memos.stream()
                .map(this::convertMemoToDTO)
                .collect(Collectors.toList());
    }

    private EventDTO convertToDTO(Event event) {
        EventDTO.EventDTOBuilder builder = EventDTO.builder();

        // eventId가 null이 아닌 경우에만 설정합니다.
        if (event.getEventId() != null) {
            builder.eventId(event.getEventId());
        }

        // 멤버가 null이 아닌 경우에만 memberId를 설정합니다.
        if (event.getMember() != null && event.getMember().getMemberId() != null) {
            builder.memberId(event.getMember().getMemberId());
        }

        // content가 null이 아닌 경우에만 설정합니다.
        if (event.getContent() != null) {
            builder.content(event.getContent());
        }

        // eventColor가 null이 아닌 경우에만 설정합니다.
        if (event.getEventColor() != null) {
            builder.eventColor(event.getEventColor());
        }

        // startDate가 null이 아닌 경우에만 설정합니다.
        if (event.getStartDate() != null) {
            builder.startDate(event.getStartDate());
        }

        // endDate가 null이 아닌 경우에만 설정합니다.
        if (event.getEndDate() != null) {
            builder.endDate(event.getEndDate());
        }

        // cycle가 null이 아닌 경우에만 설정합니다.
        if (event.getCycle() != null) {
            builder.cycle(event.getCycle());
        }

        return builder.build();
    }

    private MemoDTO convertMemoToDTO(Memo memo) {
        MemoDTO.MemoDTOBuilder builder = MemoDTO.builder();

        // eventId가 null이 아닌 경우에만 설정합니다.
        if (memo.getMemoId() != null) {
            builder.memoId(memo.getMemoId());
        }


        // content가 null이 아닌 경우에만 설정합니다.
        if (memo.getContent() != null) {
            builder.content(memo.getContent());
        }

        // memo_date가 null이 아닌 경우에만 설정합니다.
        if (memo.getMemoDate() != null) {
            builder.memoDate(memo.getMemoDate().atStartOfDay());
        }

        return builder.build();
    }

}
