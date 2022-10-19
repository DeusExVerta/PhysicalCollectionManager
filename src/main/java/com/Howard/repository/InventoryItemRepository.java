package com.Howard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem,Long> {
		public InventoryItem findByInventoryAndName(Inventory inventory,String name);
}
