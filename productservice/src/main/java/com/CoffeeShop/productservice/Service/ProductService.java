package com.CoffeeShop.productservice.Service;

import com.CoffeeShop.productservice.DTO.ProductResponse;
import com.CoffeeShop.productservice.Model.Product;
import com.CoffeeShop.productservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public void addProduct(Product product) {
        System.out.println("Adding Product "+ product.toString() + "to Database");
        productRepository.save(product);
    }

    public List<ProductResponse> getProducts() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for(Product product : products) {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice()).build();

            productResponseList.add(productResponse);
        }

        return productResponseList;
    }
}
