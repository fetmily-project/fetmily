package org.zerock.petmilyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.dto.EventDTO;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public void addEvent(EventDTO eventDTO) {
        // 여기에 이벤트를 추가하는 비즈니스 로직을 구현합니다.
        // 예를 들어, 데이터베이스에 저장하는 등의 작업을 수행합니다.
        System.out.println("Event added: " + eventDTO);
    }
}