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

    @JsonIgnore
    @OneToMany(mappedBy= "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public static Orders createOrders(Member member, OrderItem... orderItems) { // 주문 생성
        Orders orders = new Orders();
        System.out.println("memberTest");
        orders.setMember(member);
        orders.setAddr(member.getAddr());
        orders.setPhone(member.getPhone());

        for(OrderItem orderItem : orderItems) {
            orders.addOrderItem(orderItem);
            orders.setTotalPrice(orderItem.getTotalPrice());
        }

        orders.setStatus(OrderStatus.ORDER);
        return orders;
    }

    public void cancel(){ // 주문취소
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
}
