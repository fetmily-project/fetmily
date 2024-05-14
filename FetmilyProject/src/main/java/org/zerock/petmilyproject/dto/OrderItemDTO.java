package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.domain.Member;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    Long id;
    Item item;
    int orderPrice;
    int count;
}
