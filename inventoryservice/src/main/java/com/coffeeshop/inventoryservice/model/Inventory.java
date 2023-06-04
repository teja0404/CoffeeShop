package com.coffeeshop.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Setter
@Table(name = "inventory")
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(generator = "inventory_generator")
    @SequenceGenerator(
            name = "inventory_generator",
            sequenceName = "inventory_generator",
            initialValue = 500
    )
    private Long id;
    private String skuCode;
    private Integer quantity;
}
