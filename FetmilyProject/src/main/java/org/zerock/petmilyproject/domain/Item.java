package org.zerock.petmilyproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
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
}
