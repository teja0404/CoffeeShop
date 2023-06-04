package com.coffeeshop.orderservice.service;

import com.coffeeshop.orderservice.dto.OrderLineItemsDTO;
import com.coffeeshop.orderservice.dto.OrderRequest;
import com.coffeeshop.orderservice.model.Order;
import com.coffeeshop.orderservice.model.OrderLineItems;
import com.coffeeshop.orderservice.repository.OrderLineItemsRepository;
import com.coffeeshop.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderLineItemsRepository orderLineItemsRepository;

    private final WebClient webClient;

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    @CircuitBreaker(name = "inventory", fallbackMethod = "circuitBreakFallBackMethod")
    public String createOrder(OrderRequest orderRequest) {
            System.out.println("OrderRequest is "+ orderRequest.toString());
            String orderLineItemsListStr = getModelListStringFromDTO(orderRequest.getOrderLineItemsDTO());
            Order order = Order.builder().orderPrice(orderRequest.getOrderPrice().toString())
                    .orderLineItemsList(orderLineItemsListStr).build();

            boolean allItemsInStock = checkAllItemsInStock(orderRequest.getOrderLineItemsDTO());
            if(!allItemsInStock) {
                return "Some of the items are not in stock rightnow :(";
            }
        order =  orderRepository.save(order);
        kafkaTemplate.send("notificationTopic", order);
            return "Order placed successfully";
    }

    private boolean checkAllItemsInStock(List<OrderLineItemsDTO> orderLineItemsDTOList) {
        boolean allInStock = true;
        Set<String> skuSet = new HashSet<String>();
        for(OrderLineItemsDTO orderLineItemsDTO : orderLineItemsDTOList) {
            skuSet.add(orderLineItemsDTO.getSkuCode());
        }

        Iterator itr = skuSet.iterator();

        while (itr.hasNext()) {
            String sku = (String) itr.next();
            if(!isInStock(sku)) {
                allInStock = false;
                break;
            }
        }

        return allInStock;
    }

    private String getModelListStringFromDTO(List<OrderLineItemsDTO> orderLineItemsDTOList) {
        StringBuffer str = new StringBuffer();
        for(OrderLineItemsDTO orderLineItemsDTO : orderLineItemsDTOList) {
            OrderLineItems orderLineItems = OrderLineItems.builder().
                    price(orderLineItemsDTO.getPrice()).
                    skuCode(orderLineItemsDTO.getSkuCode()).
                    quantity(orderLineItemsDTO.getQuantity()).build();
            orderLineItems = orderLineItemsRepository.save(orderLineItems);
            str.append(orderLineItems.toString());

        }

        return str.toString();
    }

    public Boolean isInStock(String skucode) {
        return webClient.get().uri("http://localhost:8082/api/inventory/instock/" + skucode).retrieve().bodyToFlux(Boolean.class).blockFirst();
    }

    public String circuitBreakFallBackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return "OOPS !! Circut Breaker opened to mitigate the accident";
    }
}
