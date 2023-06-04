package com.coffeeshop.inventoryservice.service;

import com.coffeeshop.inventoryservice.model.Inventory;
import com.coffeeshop.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public boolean inStock(String skucode) {

        Inventory inventory = inventoryRepository.findInventoryBySKU(skucode);
        return inventory != null && inventory.getQuantity() > 0? true : false;
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
