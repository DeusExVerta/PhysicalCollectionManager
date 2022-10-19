package com.Howard.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import com.Howard.entity.Inventory;
import com.Howard.entity.User;
import com.Howard.repository.InventoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InventoryServiceImplementation implements InventoryService {
	private InventoryRepository ICRepo;
	
	@Override
	public Page<Inventory> findAll(User user, Pageable pageable) {
		return ICRepo.findByUser(user,pageable);
	}

	@Override
	public void save(Inventory itemCollection) {
		ICRepo.save(itemCollection);
	}

	@Override
	public Inventory findByName(User user, String name) {
		return ICRepo.findByUserAndName(user, name);
	}

	@Override
	public void delete(Inventory itemCollection) {
		ICRepo.delete(itemCollection);
	}

}
