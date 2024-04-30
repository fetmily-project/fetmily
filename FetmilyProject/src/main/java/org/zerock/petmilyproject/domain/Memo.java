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
public class Memo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String content;

    private LocalDateTime memoDate;
}
