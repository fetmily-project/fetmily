package org.zerock.petmilyproject.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private String info;
    private Long price;
    private Long cnt;
    private String category;
    private String itemImage;
    private String kind;
    private String brand;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}
