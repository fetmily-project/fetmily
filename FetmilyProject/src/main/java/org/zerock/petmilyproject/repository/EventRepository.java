package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.petmilyproject.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    // 추가적인 메소드가 필요한 경우 여기에 정의할 수 있습니다.
}