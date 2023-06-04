package com.coffeeshop.orderservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(generator = "order_generator")
    @SequenceGenerator(
            name = "order_generator",
            sequenceName = "order_generator",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String orderPrice;

    @Column(columnDefinition = "text")
    private String orderLineItemsList;
}
