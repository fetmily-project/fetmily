package org.zerock.petmilyproject.domain;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "order_id")
    private Orders orders;

    private int orderPrice;
    private int count;

    public void setMember(Member member){
        this.member = member;
        member.getOrderItem().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getOrderItem().add(this);
    }

    public static OrderItem createOrderItem(Member member, Item item, int orderPrice, int count){
        OrderItem orderItem = OrderItem.builder().
            member(member).
            item(item).
            orderPrice(orderPrice).
            count(count).
            build();

        item.removeStock(count);
        return orderItem;
    }

    public void changeCnt(int cnt){
        this.count = count;
    }

    public void cancel(){getItem().addStock(count);}

    public int getTotalPrice() {return getOrderPrice() * getCount();}


}
