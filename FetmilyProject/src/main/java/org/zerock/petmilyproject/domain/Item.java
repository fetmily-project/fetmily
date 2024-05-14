package org.zerock.petmilyproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerock.petmilyproject.Exception.OutOfStockException;
import org.zerock.petmilyproject.domain.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(length = 500, nullable = false)
    private String itemName;

    @Column(length = 500, nullable = false)
    private String info;

    @Column(length = 500, nullable = false)
    private int price;

    @ColumnDefault("1")
    @Column(length = 500, nullable = false)
    private int stockQuantity;

    @Column(length = 500, nullable = false)
    private String category;

    @Column(length = 500, nullable = false)
    private String brand;

    @Column(length = 1000, nullable = false)
    private String itemImage;

    @Column(length = 20, nullable = false)
    private String kind;
//    @JsonIgnore
//    @OneToMany(mappedBy = "item")
//    private List<OrderItem> orderItem = new ArrayList<>();

//    public void removeStock(int stockNumber){ // 재고 감소
//        int restStock = this.stockQuantity - stockNumber;
//        if(restStock<0){
//            throw new OutOfStockException("상품의 재고가 부족합니다. 현재 재고 : " + this.stockQuantity);
//        }
//        this.stockQuantity = restStock;
//    }
//    public void addStock(int stockNumber) { this.stockQuantity += stockNumber;} // 재고 증





}
