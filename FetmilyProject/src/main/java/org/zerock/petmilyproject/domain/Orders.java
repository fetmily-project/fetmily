package org.zerock.petmilyproject.domain;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import javax.persistence.*;
import org.zerock.petmilyproject.constant.OrderStatus;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member"})
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(length = 500)
    private String phone;

    @Column(length = 500)
    private String addr;

    //, nullable = false
    @Column(length = 500)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
