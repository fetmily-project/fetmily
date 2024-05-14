package org.zerock.petmilyproject.repository;


import lombok.Getter;
import lombok.Setter;
import org.zerock.petmilyproject.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;


}
