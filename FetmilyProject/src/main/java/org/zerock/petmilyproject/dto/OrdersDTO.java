package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Long orderId;
    private Long memberId;
    private String phone;
    private String addr;
    private Long totalPrice;
}
