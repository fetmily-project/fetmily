package org.zerock.petmilyproject.domain;

import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private Long price;

    @ColumnDefault("1")
    @Column(length = 500, nullable = false)
    private Long cnt;

    @Column(length = 500, nullable = false)
    private String category;

    @Column(length = 500, nullable = false)
    private String brand;

    @Column(length = 1000, nullable = false)
    private String itemImage;

    @Column(length = 20, nullable = false)
    private String kind;

}
