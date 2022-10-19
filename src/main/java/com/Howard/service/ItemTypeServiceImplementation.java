package com.Howard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Howard.entity.ItemType;
import com.Howard.entity.User;
import com.Howard.repository.ItemTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ItemTypeServiceImplementation implements ItemTypeService {
	private ItemTypeRepository ITRepo;
	@Override
	public List<ItemType> findByUser(User user) {
		log.info("finding all types for user "+user.getEmail());
		return ITRepo.findByUser(user);
	}
	
	@Override
	public Boolean existsByName(String name) {
		return ITRepo.existsByName(name);
	}

	@Override
	public ItemType findByName(String name) {
		return ITRepo.findByName(name);
	}

	@Override
	public void save(ItemType type) {
		log.info(String.format("Saving new type: %s, with ID %d", type.getName(),type.getId()));
		ITRepo.save(type);
	}

}
