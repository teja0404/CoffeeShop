package com.coffeeshop.inventoryservice.controllers;

import com.coffeeshop.inventoryservice.model.Inventory;
import com.coffeeshop.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/health")
    public String getHealth() {
        return "Inventory service is running";
    }

    @GetMapping("/api/inventory/instock/{skucode}")
    public boolean inStock(@PathVariable("skucode") String skucode) {
        return inventoryService.inStock(skucode);
    }

    @PostMapping("/api/inventory/inventory")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }
}
