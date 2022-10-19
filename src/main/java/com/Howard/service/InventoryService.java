package com.Howard.service;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.Howard.entity.Inventory;
import com.Howard.entity.User;

public interface InventoryService {
	public Page<Inventory> findAll(User user, Pageable pageable);
	public Inventory findByName(User user, String name);
	public void save(Inventory itemCollection);
	public void delete(Inventory itemCollection);
}
