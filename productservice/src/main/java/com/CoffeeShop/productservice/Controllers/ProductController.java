package com.CoffeeShop.productservice.Controllers;

import com.CoffeeShop.productservice.DTO.ProductResponse;
import com.CoffeeShop.productservice.Model.Product;
import com.CoffeeShop.productservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.CoffeeShop.productservice.DTO.ProductRequest;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/product/health")
    public String checkHealth() {
        return "Products service is up and running";
    }

    @PostMapping("/api/product/product")
    @ResponseStatus(HttpStatus.CREATED)
    public String addProduct(@RequestBody ProductRequest productRequest) {
        Product product = Product.builder().
                name(productRequest.getName()).
                price(productRequest.getPrice()).
                description(productRequest.getDescription()).
                build();

        productService.addProduct(product);

        return "Product added succesfully";
    }

    @GetMapping("/api/product/products")
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }
}
