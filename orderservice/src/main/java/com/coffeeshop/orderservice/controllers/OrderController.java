package com.coffeeshop.orderservice.controllers;

import com.coffeeshop.orderservice.dto.OrderRequest;
import com.coffeeshop.orderservice.model.Order;
import com.coffeeshop.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/order/health")
    public String getHeath() {
        return "Order service is running";
    }

    @GetMapping("/api/order/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/api/order/order")
    public String createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

}
