package com.Howard.service;

import org.springframework.stereotype.Service;

import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;
import com.Howard.repository.InventoryItemRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InventoryItemServiceImplementation implements InventoryItemService {

	InventoryItemRepository IiRepo;
	@Override
	public InventoryItem findByInventoryAndName(Inventory inventory, String name) {
		return IiRepo.findByInventoryAndName(inventory,name);
	}

}
