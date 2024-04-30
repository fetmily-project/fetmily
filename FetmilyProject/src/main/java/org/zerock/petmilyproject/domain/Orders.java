package org.zerock.petmilyproject.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 500, nullable = false)
    private String phone;

    @Column(length = 500, nullable = false)
    private String addr;

    @Column(length = 500, nullable = false)
    private Long totalPrice;
}
