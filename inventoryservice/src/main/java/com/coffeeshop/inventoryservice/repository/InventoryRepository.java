package com.coffeeshop.inventoryservice.repository;

import com.coffeeshop.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.skuCode=:skuCode")
    Inventory findInventoryBySKU(@Param("skuCode") String skuCode);
}
