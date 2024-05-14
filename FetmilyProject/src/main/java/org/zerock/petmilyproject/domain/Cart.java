package org.zerock.petmilyproject.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long cnt;

    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long status;

    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long cartPrice;

    public void changeCnt(Long cnt){
        this.cnt = cnt;
    }

    public static Cart createCart(Item item, long count){
            Cart cart = Cart.builder().
//            item(item).
//            cnt(count).
//            cartPrice(item.getPrice()).
            build();
//            item.removeStock(count);
            return cart;
    }
    public Long getTotalPrice(){
        return cartPrice*cnt;
    }
}
