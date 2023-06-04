package com.CoffeeShop.productservice.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private String price;

}
