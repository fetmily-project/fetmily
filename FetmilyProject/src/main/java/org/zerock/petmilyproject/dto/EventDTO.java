package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long eventId;
    private Long memberId;
    private String content;
    private String eventColor;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long cycle;
}
