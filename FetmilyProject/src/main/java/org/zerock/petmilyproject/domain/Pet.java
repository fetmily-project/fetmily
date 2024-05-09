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
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(length = 500, nullable = false)
    private String petName;

    @Column(length = 500, nullable = false)
    private String petType;

    @Column(length = 500)
    private LocalDateTime birth;

    @Column(length = 500)
    private String weight;

    @Column(length = 500)
    private String neut;

    @Column(length = 500)
    private String sex;

    @Column(length = 1000)
    private String etc;
}
