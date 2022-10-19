package com.Howard.service;

import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;

public interface InventoryItemService {
	public InventoryItem findByInventoryAndName(Inventory inventory,String name);
}
