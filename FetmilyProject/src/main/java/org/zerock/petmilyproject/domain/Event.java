package org.zerock.petmilyproject.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String content;

    private String eventColor;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long cycle;

    public void setContent(String content) {
    }

    public void setEventColor(String eventColor) {
    }

    public void setStartDate(LocalDateTime startDate) {
    }

    public void setEndDate(LocalDateTime endDate) {
    }

    public void setCycle(Long cycle) {
    }
}
