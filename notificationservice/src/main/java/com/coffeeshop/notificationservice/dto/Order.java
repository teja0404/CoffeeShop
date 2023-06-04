package com.coffeeshop.notificationservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Long id;
    private String orderPrice;
    private String orderLineItemsList;
}
