package com.coffeeshop.orderservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_line_items")
@Builder
@ToString
public class OrderLineItems {
    @Id
    @GeneratedValue(generator = "order_line_items_generator")
    @SequenceGenerator(
            name = "order_line_items_generator",
            sequenceName = "order_line_items_generator",
            initialValue = 100
    )
    private Long id;
    @NotBlank
    @Column(columnDefinition = "text")
    private String skuCode;
    @NotBlank
    @Column(columnDefinition = "text")
    private String price;
    @NotBlank
    @Column(columnDefinition = "text")
    private String quantity;
}