package org.zerock.petmilyproject.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 500, nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String password;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String socialNumber;

    @Column(length = 500)
    private String phone;

    @Column(length = 500)
    private String addr;

    @Column(length = 500, nullable = false)
    private String nickname;
}
