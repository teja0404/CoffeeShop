package com.coffeeshop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemsDTO {
    private Long id;
    private String skuCode;
    private String price;
    private String quantity;
}
