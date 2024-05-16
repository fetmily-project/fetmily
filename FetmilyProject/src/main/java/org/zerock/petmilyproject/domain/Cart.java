package org.zerock.petmilyproject.domain;

import java.util.List;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import org.zerock.petmilyproject.dto.CartDTO;

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


    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long cnt;

    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long status;

    @ColumnDefault("0")
    @Column(length = 500, nullable = false)
    private Long cartPrice;

    public void changeCnt(Long cnt) {
        this.cnt += cnt;
    }


    public Long getTotalPrice() {
        return cartPrice * cnt;
    }

    public void changeStatus() {
        this.status = 1L;
    }


}

