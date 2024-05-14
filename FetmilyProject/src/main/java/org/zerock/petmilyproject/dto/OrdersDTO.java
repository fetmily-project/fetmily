package org.zerock.petmilyproject.dto;

import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zerock.petmilyproject.domain.Orders;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Long ordersId;
    private Long memberId;
    private Long itemId;
    private String phone;
    private String addr;
    private int totalPrice;

}
