package com.CoffeeShop.productservice.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@Getter
@Setter
@Builder
@ToString
public class Product {

    private String id;
    private String name;
    private String description;
    private String price;
}
